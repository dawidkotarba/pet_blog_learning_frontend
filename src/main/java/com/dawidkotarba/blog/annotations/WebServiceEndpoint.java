package com.dawidkotarba.blog.annotations;

import javax.inject.Named;
import javax.jws.WebService;
import java.lang.annotation.*;

/**
 * Created by Dawid Kotarba on 06.03.2016.
 */

@Named
@WebService
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WebServiceEndpoint {
}
