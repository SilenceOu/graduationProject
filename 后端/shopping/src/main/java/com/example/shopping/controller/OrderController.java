package com.example.shopping.controller;

import com.example.shopping.model.Order;
import com.example.shopping.model.PageResult;
import com.example.shopping.result.Result;
import com.example.shopping.result.ResultFactory;
import com.example.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/search")
    public Result search(Order order){
        PageResult pageResult = orderService.search(order);
        return ResultFactory.buildSuccessResult(pageResult);
    }

    @PostMapping("/searchByUser")
    public Result searchByUser(Order order){
        PageResult pageResult = orderService.searchByUser(order);
        return ResultFactory.buildSuccessResult(pageResult);

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
