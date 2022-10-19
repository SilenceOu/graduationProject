package com.example.shopping.model;

import lombok.Data;

import java.util.List;
@Data
public class CommodityResult {
    private List<Commodity> commodityList;
    private String memo;
}
