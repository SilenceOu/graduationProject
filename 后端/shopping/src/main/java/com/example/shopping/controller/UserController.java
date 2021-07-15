package com.example.shopping.controller;

import com.example.shopping.model.Address;
import com.example.shopping.model.Cart;
import com.example.shopping.model.LoginMessage;
import com.example.shopping.model.User;
import com.example.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<User> search(User user){
        return userService.search(user);
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

    @RequestMapping(value = "/count",method = RequestMethod.POST)
    public Integer count(User user){
        return userService.getTotalCount(user);
    }

    @RequestMapping(value = "/getCart",method = RequestMethod.POST)
    public List<Cart> getCart(Integer userId){
        return userService.getCart(userId);
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

    @RequestMapping(value = "/getAddress",method = RequestMethod.POST)
    public List<Address> getAddress(Address address){
        return userService.getAddress(address);
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
