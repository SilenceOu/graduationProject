package com.example.shopping.dao;

import com.example.shopping.model.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityDao {
    //根据实体条件去查询
    public List<Commodity> search(Commodity commodity);
    //根据实体条件去查询上架商品
    public List<Commodity> searchByStore(Commodity commodity);
    //根据ID查找
    public Commodity selectOne(Integer id);
    //根据ID修改记录
    public Integer updateOne(Commodity commodity);
    //插入一条新纪录
    public Integer insertOne(Commodity commodity);
    //根据ID修改商品状态
    public Integer updateStatus(Commodity commodity);
    //根据商品名称搜索
    public Commodity selectByName(String name);
    //根据商品名称去查商品数量
    public Integer selectNumByName(String name);
    //根据商品名称修改商品数量
    public Integer updateNumByName(@Param(value = "name")String name, @Param(value = "number")int number);
}
