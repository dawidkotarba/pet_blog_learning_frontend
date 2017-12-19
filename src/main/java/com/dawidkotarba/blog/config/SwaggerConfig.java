package com.dawidkotarba.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Created by Dawid Kotarba on 12.11.2015.
 */

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(Collections.singletonList(getBasicAuthField()))
                .pathMapping("/");
    }

    private Parameter getBasicAuthField() {
        return new ParameterBuilder()
                .parameterType("header")
                .modelRef(new ModelRef("String"))
                .required(false)
                .name("Authorization")
                .description("Base64 encoded username:password -> Basic [encoded]")
                .build();
    }
}
