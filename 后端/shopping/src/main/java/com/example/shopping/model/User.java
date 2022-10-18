package com.example.shopping.model;

import lombok.Data;

import java.util.Map;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String avatar;
    private Map<String,Integer> pageable;
}

