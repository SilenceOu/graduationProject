package com.example.shopping.service.impl;

import com.example.shopping.dao.CartDao;
import com.example.shopping.dao.CommodityDao;
import com.example.shopping.model.Comm;
import com.example.shopping.model.Commodity;
import com.example.shopping.model.CountNumber;
import com.example.shopping.model.CommodityResult;
import com.example.shopping.service.CommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Resource
    private CommodityDao commodityDao;
    @Resource
    private CartDao cartDao;
    @Override
    public List<Commodity> search(Commodity commodity) {
        return commodityDao.search(commodity);
    }


    @Override
    public List<Commodity> searchByStore(Commodity commodity) {
        return commodityDao.searchByStore(commodity);
    }

    @Override
    public Commodity selectOne(Integer id) {
        return commodityDao.selectOne(id);
    }

    @Override
    public String updateOne(Commodity commodity) {
        int count=commodityDao.updateOne(commodity);
        if (count>0){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @Override
    public String create(Commodity commodity) {
        Integer count = commodityDao.insertOne(commodity);
        if (count>0){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }

    @Override
    public void updateStatus(Commodity commodity) {
        commodityDao.updateStatus(commodity);
    }

    @Override
    public CommodityResult countNumber(List<CountNumber> countNumber) {
        int index = 0, num = 0;
        List<Commodity> commodityList = new ArrayList<>();
        CommodityResult commodityResult = new CommodityResult();
        for (CountNumber count : countNumber) {
            Commodity commodity = commodityDao.selectByName(count.getName());
            if (commodity != null) {
                index++;
                if (commodity.getNumber() >= count.getNumber()) {
                    num++;
                } else {
                    commodityList.add(commodity);
                }

            }
        }
        if (index == countNumber.size() && num == countNumber.size()) {
            commodityResult.setMemo("可以下单");
            return commodityResult;
        } else if (index == countNumber.size() && num < countNumber.size()) {
            commodityResult.setCommodityList(commodityList);
            return commodityResult;
        }else
            return commodityResult;
    }

    @Override
    public void changeNumber(List<Comm> comm) {

        for (Comm c : comm) {
            String name=c.getName();
            Integer number = commodityDao.selectNumByName(name);
            number=number-c.getNumber();
            commodityDao.updateNumByName(name,number);
        }
    }
}
