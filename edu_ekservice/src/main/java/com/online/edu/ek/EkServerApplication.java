package com.online.edu.ek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer//表示是一个注册中心的服务器
public class EkServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EkServerApplication.class, args);
    }
}
