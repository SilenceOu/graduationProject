package com.example.shopping.controller;

import com.example.shopping.model.Manager;
import com.example.shopping.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @RequestMapping(value = "/login",method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String login(Manager manager){
        return managerService.login(manager);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(Manager manager){
        return managerService.register(manager);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public List<Manager> search(Manager manager){
        return managerService.search(manager);
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Manager get(Integer id){
        return managerService.selectOne(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String update(Manager manager){
        return managerService.updateOne(manager);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(Integer id){
        return managerService.deleteOne(id);
    }

    @RequestMapping(value = "/count",method = RequestMethod.POST)
    public Integer count(Manager manager){
        return managerService.getTotalCount(manager);
    }
}
