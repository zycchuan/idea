package com.test.controller;

import com.test.domain.Product;
import com.test.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Value("${server.port}")
    private String port;
    @RequestMapping("selectAll")
    public List<Product> selectAll(){

        return productService.selectAll();
    }

    @RequestMapping("selectOne")
    public Product selectOne(int id){
        Product product = productService.selectOne(id);
        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(product.getName()+"data from "+port);
        return result;
    }
}
