package com.example.shopping.controller;

import com.example.shopping.model.Comm;
import com.example.shopping.model.Commodity;
import com.example.shopping.model.CountNumber;
import com.example.shopping.model.Result;
import com.example.shopping.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    private CommodityService commodityService;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<Commodity> search(Commodity commodity){
        return commodityService.search(commodity);
    }

    @RequestMapping(value = "/count",method = RequestMethod.POST)
    public Integer count(Commodity commodity){
        return commodityService.getTotalCount(commodity);
    }

    @RequestMapping(value = "/searchByStore",method = RequestMethod.POST)
    public List<Commodity> searchByStore(Commodity commodity){
        return commodityService.searchByStore(commodity);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Commodity get(Integer id){
        return commodityService.selectOne(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Commodity commodity){
        return commodityService.updateOne(commodity);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String create(Commodity commodity){
        return commodityService.create(commodity);
    }

    @RequestMapping(value = "/updateStatus",method = RequestMethod.POST)
    public void updateStatus(Commodity commodity){
        commodityService.updateStatus(commodity);
    }

    @RequestMapping(value = "/getDetails",method = RequestMethod.POST)
    public Commodity getDetails(Integer id){
        return commodityService.selectOne(id);
    }

    @RequestMapping(value = "/countNumber",method = RequestMethod.POST)
    public Result countNumber(@RequestBody List<CountNumber> countNumber){
        return commodityService.countNumber(countNumber);
    }

    @RequestMapping(value = "/changeNumber",method = RequestMethod.POST)
    public void changeNumber(@RequestBody List<Comm> comm){
        commodityService.changeNumber(comm);
    }
}
