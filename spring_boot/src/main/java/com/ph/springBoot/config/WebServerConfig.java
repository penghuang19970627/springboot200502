package com.ph.springBoot.config;

import com.ph.springBoot.filter.RequestParamFilter;
import com.ph.springBoot.interceptor.RequestViewInterceptor;
import jdk.nashorn.internal.runtime.Context;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WebServerConfig implements WebMvcConfigurer {
    @Value("${server.http.port}")
    private int httpPort;

    @Autowired
    private RequestViewInterceptor requestViewInterceptor;

    @Bean
    public Connector connector(){
        Connector connector = new Connector();
        connector.setScheme("http");
        connector.setPort(httpPort);
        return connector;
    }

    @Bean
    public ServletWebServerFactory servletWebServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addAdditionalTomcatConnectors(connector());
        return factory;
    }

    @Bean
    public FilterRegistrationBean<RequestParamFilter> register(){
        FilterRegistrationBean<RequestParamFilter> register = new FilterRegistrationBean<RequestParamFilter>();
        register.setFilter(new RequestParamFilter());
        return register;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/**");
    }
}
