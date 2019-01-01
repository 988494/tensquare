package com.tensquare.search.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author 杨郑兴
 * @Date 2018/12/25 20:52
 * @官网 www.weifuwukt.com
 */
@SpringBootApplication
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }

    @Bean(value = "idWorker")
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
