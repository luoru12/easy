package com.york.easy.easyread;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.york.easy.easyread.mapper")
@ComponentScan("com.york.easy.easyread.*")
public class EasyReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyReadApplication.class, args);
    }

}
