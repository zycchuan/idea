package com.test.service.impl;

import com.test.domain.Product;
import com.test.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Map<Object,Product> productList= new HashMap<Object,Product>();

    static {
        Product p1 = new Product(1, "zyc1", 2, 3);
        Product p2 = new Product(2, "zyc2", 2, 3);
        Product p3 = new Product(3, "zyc3", 2, 3);
        Product p4 = new Product(4, "zyc4", 2, 3);
        Product p5 = new Product(5, "zyc5", 2, 3);
        Product p6 = new Product(6, "zyc6", 2, 3);
        productList.put(p1.getId(),p1);
        productList.put(p2.getId(),p2);
        productList.put(p3.getId(),p3);
        productList.put(p4.getId(),p4);
        productList.put(p5.getId(),p5);
        productList.put(p6.getId(),p6);

    }


    @Override
    public List<Product> selectAll() {
        Collection<Product> values = productList.values();
        List<Product> list = new ArrayList<>(values);
        return list;
    }

    @Override
    public Product selectOne(int id) {
        return productList.get(id);
    }
}
