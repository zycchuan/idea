package com.test.domain;

import jdk.nashorn.internal.ir.debug.PrintVisitor;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private String productName;
    private String tradeNo;
    private int price;
    private Date createTime;
    private int userId;
    private String username;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Order() {
    }

    public Order(int id, String productName, String tradeNo, int price, Date createTime, int userId, String username) {
        this.id = id;
        this.productName = productName;
        this.tradeNo = tradeNo;
        this.price = price;
        this.createTime = createTime;
        this.userId = userId;
        this.username = username;
    }
}
