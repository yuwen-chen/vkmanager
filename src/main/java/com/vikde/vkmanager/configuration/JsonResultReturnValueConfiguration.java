package com.vikde.vkmanager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author vikde
 * @date 2017/12/6
 */
@Configuration
public class JsonResultReturnValueConfiguration extends WebMvcConfigurerAdapter {
    @Resource
    private JsonResultReturnValueHandler jsonResultReturnValueHandler;

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(jsonResultReturnValueHandler);
    }
}
