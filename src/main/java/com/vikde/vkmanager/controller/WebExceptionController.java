package com.vikde.vkmanager.controller;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created on 2017/2/21.
 *
 * @author vikde
 */
@ControllerAdvice
public class WebExceptionController {

    @ExceptionHandler
    public String handleException(Exception exception) {
        return "404";
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/404.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/404.html"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        };
    }
}
