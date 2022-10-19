package com.example.shopping.service;

import com.example.shopping.model.*;

import java.util.List;

public interface UserService {
    //登录用户界面
    public LoginMessage login(User user);
    //注册用户
    public String register(User user);
    //根据实体条件去查询
    public List<User> search(User user);
    //根据ID查找
    public User selectOne(Integer id);
    //根据ID修改记录
    public String updateOne(User user);
    //根据ID删除记录
    public String deleteOne(Integer id);
    //获取购物车列表
    PageResult getCart(Cart cart);
    //根据数量修改商品总价
    public void updateCart(Cart cart);
    //加入购物车
    public String saveCart(Cart cart);
    //删除用户购物车
    public void deleteCart(Integer id);
    //获取用户收货地址
    public List<Address> getAddress(Address address);
    //更新用户收货地址
    public void updateAddress(Address address);
    //新增用户收货地址
    public void saveAddress(Address address);
    //删除用户收货地址
    public void deleteAddress(Integer id);
}
