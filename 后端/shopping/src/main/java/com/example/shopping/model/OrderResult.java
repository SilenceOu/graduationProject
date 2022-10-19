package com.example.shopping.model;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: SilenceOu
 * @Date: 2022/10/19 22:15
 */
@Data
public class OrderResult {
    private List<Order> orderList;
    private Integer total;
}
