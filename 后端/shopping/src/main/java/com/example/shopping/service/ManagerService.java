package com.example.shopping.service;

import com.example.shopping.model.Manager;

import java.util.List;

public interface ManagerService {
    //登录后台管理界面
    public String login(Manager manager);
    //注册管理员
    public String register(Manager manager);
    //根据实体条件去查询
    public List<Manager> search(Manager manager);
    //根据ID查找
    public Manager selectOne(Integer id);
    //根据ID修改记录
    public String updateOne(Manager manager);
    //根据ID删除记录
    public String deleteOne(Integer id);
}
