package com.wtchuigo.reunion.config.swaggerui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration(proxyBeanMethods = false)
public class MyConfigurer {

    @Bean
    public HttpMessageConverters customConverters() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.addAll(converter.getSupportedMediaTypes());
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        return new HttpMessageConverters(converter);
    }

}
