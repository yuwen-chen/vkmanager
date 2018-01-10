package com.vikde.vkmanager.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 * Created on 2017/07/02.
 * 使用fastJson
 * 只有在responseBody与requestBody注解上才生效
 *
 * @author vikde
 */
@Configuration
public class FastJsonConfiguration {
    @Resource
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverter;

    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        fastJsonHttpMessageConverter.setDefaultCharset(Charset.forName("utf-8"));
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        return fastJsonHttpMessageConverter;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        return new HttpMessageConverters((HttpMessageConverter<?>) fastJsonHttpMessageConverter);
    }
}
