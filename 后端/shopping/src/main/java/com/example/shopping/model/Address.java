package com.example.shopping.model;

import lombok.Data;

import java.util.Map;

@Data
public class Address {
    private int id;
    private String name;
    private String phone;
    private String city;
    private String detailAddress;
    private int userId;
    private Map<String,Integer> pageable;
}
