package com.york.easywrite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.york.easywrite.mapper")
@ComponentScan("com.york.easywrite.*")
@EnableEurekaClient
public class EasywriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasywriteApplication.class, args);
    }

}
