package com.example.shopping.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Order {
    private int id;
    private int userId;
    private String name;
    private String phone;
    private int addressId;
    private Address address;
    private String status;
    private int allMoney;
    private String commodityIds;
    private List<Commodity> commodityList;
    private int [] cartIdList;
    private int[] nums;
    private String number;
    private int[] numbers;
    private String[] names;
    private Map<String,Integer> pageable;
}
