package com.vikde.vkmanager.controller;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ApiResponseBody
public @interface ApiController {
}
