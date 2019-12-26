package com.test.service;

import com.test.failback.ProductFailBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="product-service",fallback = ProductFailBack.class)
public interface ProductFeignService {
    @RequestMapping("/product/selectOne")
    public Object findById(@RequestParam("id") int id);
}
