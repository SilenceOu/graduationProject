package com.example.shopping.dao;

import com.example.shopping.model.Manager;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerDao {
    //根据用户名查询
    public List<Manager> findByUname(Manager manager);
    //根据用户名和密码查询
    public List<Manager> findByUnameAndPwd(Manager manager);
    //插入一条新纪录
    public Integer insertOne(Manager manager);
    //根据实体条件去查询
    List<Manager> search(Manager manager);
    //根据ID查找
    public Manager selectOne(Integer id);
    //根据ID修改记录
    public Integer updateOne(Manager manager);
    //根据ID删除记录
    public Integer deleteOne(Integer id);
}
