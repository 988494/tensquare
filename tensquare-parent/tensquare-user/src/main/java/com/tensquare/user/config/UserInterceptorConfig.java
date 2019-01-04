package com.tensquare.user.config;

import com.tensquare.user.interceptor.UserJwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 杨郑兴
 * @Date 2018/12/31 23:09
 * @官网 www.weifuwukt.com
 */
@Component
@Configuration
public class UserInterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private UserJwtInterceptor jwtInterceptor;
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/**/login/**");
    }
}
