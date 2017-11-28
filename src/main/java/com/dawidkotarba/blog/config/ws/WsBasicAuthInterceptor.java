package com.dawidkotarba.blog.config.ws;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.Conduit;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Based on the concepts outline here:
 * http://chrisdail.com/2008/03/31/apache-cxf-with-http-basic-authentication
 */

@Slf4j
public class WsBasicAuthInterceptor extends SoapHeaderInterceptor {

    /**
     * Map of allowed users to this system with their corresponding passwords.
     */
    private final Map<String, String> users;

    public WsBasicAuthInterceptor(Map<String, String> users) {
        this.users = users;
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
        if (policy == null) {
            if (log.isDebugEnabled()) {
                log.debug("User attempted to log in with no credentials");
            }
            sendErrorResponse(message, HttpURLConnection.HTTP_UNAUTHORIZED);
            return;
        }

        if (log.isDebugEnabled()) {
            log.debug("Logging in use: " + policy.getUserName());
        }

        String realPassword = users.get(policy.getUserName());
        if (realPassword == null || !realPassword.equals(policy.getPassword())) {
            log.warn("Invalid username or password for user: " + policy.getUserName());
            sendErrorResponse(message, HttpURLConnection.HTTP_FORBIDDEN);
        }
    }

    private void sendErrorResponse(Message message, int responseCode) {
        Message outMessage = getOutMessage(message);
        outMessage.put(Message.RESPONSE_CODE, responseCode);

        Map<String, List<String>> responseHeaders =
            (Map<String, List<String>>) message.get(Message.PROTOCOL_HEADERS);
        if (responseHeaders != null) {
            responseHeaders.put("WWW-Authenticate", Arrays.asList(new String[] {"Basic realm=realm"}));
            responseHeaders.put("Content-Length", Arrays.asList(new String[] {"0"}));
        }
        message.getInterceptorChain().abort();
        try {
            getConduit(message).prepare(outMessage);
            close(outMessage);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }
    }

    private Message getOutMessage(Message inMessage) {
        Exchange exchange = inMessage.getExchange();
        Message outMessage = exchange.getOutMessage();
        if (outMessage == null) {
            Endpoint endpoint = exchange.get(Endpoint.class);
            outMessage = endpoint.getBinding().createMessage();
            exchange.setOutMessage(outMessage);
        }
        outMessage.putAll(inMessage);
        return outMessage;
    }

    private Conduit getConduit(Message inMessage) throws IOException {
        Exchange exchange = inMessage.getExchange();
        Conduit conduit = exchange.getDestination().getBackChannel(inMessage);
        exchange.setConduit(conduit);
        return conduit;
    }

    private void close(Message outMessage) throws IOException {
        OutputStream os = outMessage.getContent(OutputStream.class);
        os.flush();
        os.close();
    }
}