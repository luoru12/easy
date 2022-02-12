package com.york.easy.easyread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EasyReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyReadApplication.class, args);
    }

}
