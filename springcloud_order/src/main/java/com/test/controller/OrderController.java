package com.test.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.service.ProductFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private ProductFeignService productFeignService;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @RequestMapping("selectProduct")
    public Object selectProduct(){
        Object forObject = restTemplate.getForObject("http://product-service/product/selectOne?id=1", Object.class);
        return forObject;
    }

    @RequestMapping("selectOne")
    public Object selectOne(){
        //第二种方式调用  //用来书写调用服务名称  这是原来的
        ServiceInstance serviceInstance = loadBalancerClient.choose("product-service");
        System.out.println(serviceInstance.getHost());
        System.out.println(serviceInstance.getPort());

        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/selectOne?id=1",
                String.class);
        return forObject;
    }


    @RequestMapping("selectTest")
    public Object selectTest(){
        return "123";
    }


    @RequestMapping("selectFeign")
    @HystrixCommand(fallbackMethod = "savaOrderFail")
    public Object selectFeign(int id){
        return productFeignService.findById(id);
    }
    //参数要和加注解的那个方法一致
    public Object savaOrderFail(int id){
        Map<String, Object> map = new HashMap<>();
        map.put("code",-1);
        map.put("msg","失败");
        return map;
    }
}
