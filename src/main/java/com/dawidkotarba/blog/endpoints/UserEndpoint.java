package com.dawidkotarba.blog.endpoints;

import com.dawidkotarba.blog.annotations.WebServiceEndpoint;
import com.dawidkotarba.blog.dto.UserDto;
import com.dawidkotarba.blog.facade.UserFacade;

import javax.inject.Inject;
import javax.jws.WebMethod;
import java.util.List;

/**
 * Created by Dawid Kotarba on 06.03.2016.
 */

@WebServiceEndpoint
public class UserEndpoint {

    @Inject
    private UserFacade userFacade;

    @WebMethod
    public List<UserDto> findAll() {
        return userFacade.findAll();
    }
}
