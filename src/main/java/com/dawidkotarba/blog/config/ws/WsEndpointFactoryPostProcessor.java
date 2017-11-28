package com.dawidkotarba.blog.config.ws;


import com.dawidkotarba.blog.annotations.WebServiceEndpoint;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.spring.NamespaceHandler;
import org.apache.cxf.message.Message;
import org.springframework.aop.TargetClassAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.List;

/**
 * Created by Dawid Kotarba on 06.03.2016.
 */

public class WsEndpointFactoryPostProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    private List<Interceptor<? extends Message>> inInterceptors;

    public WsEndpointFactoryPostProcessor(ApplicationContext applicationContext, List<Interceptor<? extends Message>> inInterceptors) {
        this.applicationContext = applicationContext;
        this.inInterceptors = inInterceptors;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> serviceClass = bean.getClass();

        if (bean instanceof TargetClassAware) {
            serviceClass = ((TargetClassAware) bean).getTargetClass();
        }

        Package pack = serviceClass.getPackage();

        if (pack != null && AnnotationUtils.findAnnotation(bean.getClass(), WebServiceEndpoint.class) != null) {
            NamespaceHandler.SpringServerFactoryBean factoryBean = new NamespaceHandler.SpringServerFactoryBean();
            factoryBean.setServiceClass(serviceClass);
            factoryBean.setServiceBean(bean);
            factoryBean.setAddress("/" + serviceClass.getSimpleName());
            factoryBean.setApplicationContext(applicationContext);

            if (inInterceptors != null) {
                factoryBean.setInInterceptors(inInterceptors);
            }

            factoryBean.create();
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
