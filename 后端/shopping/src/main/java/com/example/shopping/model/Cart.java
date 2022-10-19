package com.example.shopping.model;

import lombok.Data;

import java.util.Map;

@Data
public class Cart {
    private int id;
    private int commodityId;
    private int number;
    private int amount;         //商品总价
    private int userId;
    private String name;        //商品名称
    private String image;       //商品图片
    private int money;          //商品单价
    private Map<String,Integer> pageable;
}