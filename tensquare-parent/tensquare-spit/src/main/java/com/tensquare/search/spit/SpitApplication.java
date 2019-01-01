package com.tensquare.search.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author 杨郑兴
 * @Date 2018/12/27 18:51
 * @官网 www.weifuwukt.com
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
