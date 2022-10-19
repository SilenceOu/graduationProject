package com.example.shopping.controller;

import com.example.shopping.model.Order;
import com.example.shopping.model.OrderResult;
import com.example.shopping.result.Result;
import com.example.shopping.result.ResultFactory;
import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/search")
    public Result search(Order order){
        OrderResult orderResult = orderService.search(order);
        return ResultFactory.buildSuccessResult(orderResult);
    }

    @RequestMapping(value = "/searchByUser",method = RequestMethod.POST)
    public List<Order> searchByUser(Order order){
        return orderService.searchByUser(order);
    }

    @RequestMapping(value = "/receive",method = RequestMethod.POST)
    public void receive(Order order){
        orderService.updateStatus(order);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void create(Order order){
        orderService.create(order);
    }
}
