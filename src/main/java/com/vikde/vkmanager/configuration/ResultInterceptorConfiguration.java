package com.vikde.vkmanager.configuration;

import com.vikde.vkmanager.web.interceptor.ApiInterceptor;
import com.vikde.vkmanager.web.interceptor.ApiPermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class ResultInterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Resource
    private ApiPermissionInterceptor apiPermissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiInterceptor())
                .addPathPatterns("/api/**");

        registry.addInterceptor(apiPermissionInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/api/user/initPassword");
        super.addInterceptors(registry);
    }

}