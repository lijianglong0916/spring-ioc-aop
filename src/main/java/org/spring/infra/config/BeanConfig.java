package org.spring.infra.config;

import org.spring.domain.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;

/**
 * @author jianglong.li
 * @date 2021-06-12 14:55
 **/
@Configuration
public class BeanConfig {

    @Bean
    public SimpleControllerHandlerAdapter getSimpleControllerHandlerAdapter(){
        return new SimpleControllerHandlerAdapter();
    }

    @Bean
    public HttpRequestHandlerAdapter getHttpRequestHandlerAdapter(){
        return new HttpRequestHandlerAdapter();
    }

    @Bean
    public Person getPerson(){
        return new Person();
    }
}
