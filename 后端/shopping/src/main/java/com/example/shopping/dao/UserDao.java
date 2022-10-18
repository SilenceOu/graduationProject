package com.example.shopping.dao;

import com.example.shopping.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    //根据用户名查询
    public List<User> findByUname(User user);
    //根据用户名和密码查询
    public List<User> findByUnameAndPwd(User user);
    //插入一条新纪录
    public Integer insertOne(User user);
    //根据实体条件去查询
    public List<User> search(User user);
    //根据ID查找
    public User selectOne(Integer id);
    //根据ID修改记录
    public Integer updateOne(User user);
    //根据ID删除记录
    public Integer deleteOne(Integer id);

}
