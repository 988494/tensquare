package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 杨郑兴
 * @Date 2018/12/31 13:12
 * @官网 www.weifuwukt.com
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /*
        authorizeRequests 所有security全注解配置实现配置得开端，表示开始说明需要得权限
        需要得权限分两部分：第一部分是拦截得路径，第二部分访问改路径需要的权限
            antMatchers表示拦截什么路径，permitAll表示任何权限都可以访问。直接放行所有
       anyRequest()任何的请求，authenticated认真后才能访问
       and().csrf().disable();固定写法，表示使用csrf拦截失败
        */
        http.authorizeRequests()
               .antMatchers("/**").permitAll()
               .anyRequest().authenticated()
               .and().csrf().disable();
    }
}
