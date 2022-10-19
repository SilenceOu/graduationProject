package com.example.shopping.service;

import com.example.shopping.model.Comm;
import com.example.shopping.model.Commodity;
import com.example.shopping.model.CountNumber;
import com.example.shopping.model.CommodityResult;

import java.util.List;

public interface CommodityService {
    //根据实体条件去查询
    public List<Commodity> search(Commodity commodity);
    //根据实体条件去查询上架商品
    public List<Commodity> searchByStore(Commodity commodity);
    //根据ID查找
    public Commodity selectOne(Integer id);
    //根据ID修改记录
    public String updateOne(Commodity commodity);
    //新增一条记录
    public String create(Commodity commodity);
    //根据ID修改商品状态
    public void updateStatus(Commodity commodity);
    //根据库存判断是否可以下单
    public CommodityResult countNumber(List<CountNumber> countNumber);
    //修改库存商品数量
    public void changeNumber(List<Comm> comm);
}
