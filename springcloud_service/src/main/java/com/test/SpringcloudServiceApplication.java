package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient 可不加
public class SpringcloudServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudServiceApplication.class, args);
    }

}
