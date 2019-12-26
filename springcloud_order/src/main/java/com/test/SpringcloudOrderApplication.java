package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
//@SpringCloudApplication 包含了以上
public class SpringcloudOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudOrderApplication.class, args);
    }

    @Bean
    @LoadBalanced //代表进行负载均衡策略
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
