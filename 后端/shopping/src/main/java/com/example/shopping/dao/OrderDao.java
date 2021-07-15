package com.example.shopping.dao;

import com.example.shopping.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {
    //根据实体条件去查询
    public List<Order> selectByAddressId(@Param("addressId") Integer addressId);
    //根据ID修改订单状态
    public Integer updateStatus(Order order);
    //根据用户ID去查找订单
    public List<Order> searchByUser(Order order);
    //新建订单
    public Integer create(Order order);
}
