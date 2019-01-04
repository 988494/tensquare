package com.tensquare.friend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.tensquare.common.util.IdWorker;
import com.tensquare.common.util.JwtUtil;

/**
 * @author 杨郑兴
 * @Date 2019/1/2 16:50
 * @官网 www.weifuwukt.com
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.tensquare.friend")
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class,args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
