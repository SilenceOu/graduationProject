package com.example.shopping.controller;

import com.example.shopping.model.*;
import com.example.shopping.result.Result;
import com.example.shopping.result.ResultFactory;
import com.example.shopping.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public LoginMessage login(User user){
        return userService.login(user);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(User user){
        return userService.register(user);
    }

    //分页查询用户数据
    @PostMapping("/search")
    public Result search(User user){
        PageHelper.startPage(user.getPageable().get("pageNum"),user.getPageable().get("pageSize"));
        List<User> userList = userService.search(user);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return ResultFactory.buildSuccessResult(userPageInfo);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public User get(Integer id){
        return userService.selectOne(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(User user){
        return userService.updateOne(user);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(Integer id){
        return userService.deleteOne(id);
    }

    @PostMapping("/getCart")
    public Result getCart(Cart cart){
        PageResult pageResult = userService.getCart(cart);
        return ResultFactory.buildSuccessResult(pageResult);
    }

    @RequestMapping(value = "/updateCart",method = RequestMethod.POST)
    public void updateCart(Cart cart){
        userService.updateCart(cart);
    }

    @RequestMapping(value = "/saveCart",method = RequestMethod.POST)
    public String saveCart(Cart cart){
        return userService.saveCart(cart);
    }

    @RequestMapping(value = "/deleteCart",method = RequestMethod.POST)
    public void deleteCart(Integer id){
        userService.deleteCart(id);
    }

    @PostMapping("/getAddress")
    public Result getAddress(Address address){
        PageHelper.startPage(address.getPageable().get("pageNum"),address.getPageable().get("pageSize"));
        List<Address> addressList = userService.getAddress(address);
        PageInfo<Address> addressPageInfo = new PageInfo<>(addressList);
        return ResultFactory.buildSuccessResult(addressPageInfo);
    }

    @RequestMapping(value = "/updateAddress",method = RequestMethod.POST)
    public void updateAddress(Address address){
        userService.updateAddress(address);
    }

    @RequestMapping(value = "/saveAddress",method = RequestMethod.POST)
    public void saveAddress(Address address){
        userService.saveAddress(address);
    }

    @RequestMapping(value = "/deleteAddress",method = RequestMethod.POST)
    public void deleteAddress(Integer id){
        userService.deleteAddress(id);
    }
}
