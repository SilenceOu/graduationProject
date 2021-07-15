package com.example.shopping.dao;

import com.example.shopping.model.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressDao {
    //根据实体条件userId去查询
    public List<Address> search(Address address);
    //根据ID去查询
    public Address selectOne(Integer id);
    //插入一条新纪录
    public Integer insertOne(Address address);
    //根据ID修改记录
    public Integer updateOne(Address address);
    //根据ID删除记录
    public Integer deleteOne(Integer id);
    //根据名称和电话去查找addressId
    public List<Integer> selectIdByCondition(String name,String phone);
}
