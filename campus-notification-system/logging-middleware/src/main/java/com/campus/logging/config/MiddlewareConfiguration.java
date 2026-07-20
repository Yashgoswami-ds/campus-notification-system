package com.campus.logging.config;


import com.campus.logging.middleware.LoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class MiddlewareConfiguration {


    private final LoggingFilter loggingFilter;


    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilterRegistration(){

        FilterRegistrationBean<LoggingFilter> registration =
                new FilterRegistrationBean<>();

        registration.setFilter(loggingFilter);
        registration.addUrlPatterns("/*");
        registration.setOrder(1);

        return registration;
    }

}