package com.example.shopping.service;

import com.example.shopping.model.Order;
import com.example.shopping.model.PageResult;

public interface OrderService {
    //根据实体条件去查询
    PageResult search(Order order);
    //根据ID修改订单状态
    public void updateStatus(Order order);
    //根据用户ID去查订单
    PageResult searchByUser(Order order);
    //新建订单
    public void create(Order order);
}
