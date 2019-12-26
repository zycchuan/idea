package com.test.failback;

import com.test.service.ProductFeignService;
import org.springframework.stereotype.Component;

/**
 * 针对 ProductFeignService里的接口 降级处理
 */
@Component
public class ProductFailBack implements ProductFeignService {
    @Override
    public Object findById(int id) {
        System.out.println("出错了");
        return "错误";
    }
}
