package com.example.shopping.model;

import lombok.Data;

import java.util.Map;

@Data
public class Commodity {
    private int id;
    private String name;
    private String image;
    private String content;
    private int money;
    private int number;
    private Integer status;
    private Map<String,Integer> pageable;
}
