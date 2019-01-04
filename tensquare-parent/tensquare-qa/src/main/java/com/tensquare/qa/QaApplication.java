package com.tensquare.qa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.tensquare.common.util.IdWorker;
import com.tensquare.common.util.JwtUtil;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.tensquare.qa")//如果feign接口写在当前项目里，不用指定basePackages，但是不在当前项目中，则一定要写basePackages
//否则出现类型找不到
public class QaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaApplication.class, args);
	}

	@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}

	@Bean
	public JwtUtil jwtUtil(){
		return new JwtUtil();
	}
}
