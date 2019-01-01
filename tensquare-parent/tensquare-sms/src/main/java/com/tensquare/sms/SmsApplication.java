package com.tensquare.sms;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 杨郑兴
 * @Date 2018/12/30 21:37
 * @官网 www.weifuwukt.com
 */
@SpringBootApplication
@EnableRabbit
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class,args);
    }
}
