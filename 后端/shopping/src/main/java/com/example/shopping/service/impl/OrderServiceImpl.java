package com.example.shopping.service.impl;

import com.example.shopping.dao.AddressDao;
import com.example.shopping.dao.CartDao;
import com.example.shopping.dao.CommodityDao;
import com.example.shopping.dao.OrderDao;
import com.example.shopping.model.*;
import com.example.shopping.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private AddressDao addressDao;
    @Resource
    private CommodityDao commodityDao;
    @Resource
    private CartDao cartDao;

    @Override
    public PageResult search(Order order) {
        List<Integer> addressIds = addressDao.selectIdByCondition(order.getName(), order.getPhone());
        ArrayList<Order> list = new ArrayList<>();
        for (Integer addressId : addressIds) {
            List<Order> orders = orderDao.selectByAddressId(addressId);
            for (Order order1 : orders) {
                //根据addressId查找address表
                int addressID = order1.getAddressId();
                Address address = addressDao.selectOne(addressID);
                Address address1=new Address();
                address1.setName(address.getName());
                address1.setPhone(address.getPhone());
                address1.setCity(address.getCity());
                address1.setDetailAddress(address.getDetailAddress());
                order1.setAddress(address1);
                //将number字符串转换成int数组
                String number = order1.getNumber();
                String[] strings = number.split(",");
                int[] numbers=new int[strings.length];
                for(int i=0;i<strings.length;i++){
                    numbers[i] = Integer.parseInt(strings[i]);
                }
                order1.setNumbers(numbers);
                //根据商品ID列查找商品
                List<Commodity> commodities=new ArrayList<>();
                String commodityIds = order1.getCommodityIds();
                String[] commodityIDs = commodityIds.split(",");
                for (String commodityID : commodityIDs) {
                    int commodityId =Integer.valueOf(commodityID);
                    Commodity commodity = commodityDao.selectOne(commodityId);
                    commodities.add(commodity);
                }
                order1.setCommodityList(commodities);
            }
            //集合累加
            list.addAll(orders);
        }

        //这里分页既不能用pagehleper，也不能用sql中的limit语句，所以只能用暴力算法来分页了
        //分页前的总数total
        Integer total = list.size();
        //开始索引
        Integer beginIndex = (order.getPageable().get("pageNum") - 1) * order.getPageable().get("pageSize");
        //结束索引
        Integer endIndex = order.getPageable().get("pageNum") * order.getPageable().get("pageSize");
        //分页后的list
        //注意索引越界异常，因此执行subList方法前要校验结束索引是否大于总数，若大于则将总数赋值给结束索引
        if (endIndex > total){
            endIndex = total;
        }
        List<Order> orderList = list.subList(beginIndex, endIndex);
        PageResult pageResult = new PageResult<Order>();
        pageResult.setTotal(total);
        pageResult.setList(orderList);
        return pageResult;
    }

    @Override
    public void updateStatus(Order order) {
        orderDao.updateStatus(order);
    }

    @Override
    public PageResult searchByUser(Order order) {
        List<Order> orders = orderDao.searchByUser(order);
        for (Order order1 : orders) {
            //根据addressId查找address表
            int addressId = order1.getAddressId();
            Address address = addressDao.selectOne(addressId);
            Address address1=new Address();
            address1.setName(address.getName());
            address1.setPhone(address.getPhone());
            address1.setCity(address.getCity());
            address1.setDetailAddress(address.getDetailAddress());
            order1.setAddress(address1);
            //将number字符串转换成int数组
            String number = order1.getNumber();
            String[] strings = number.split(",");
            int[] numbers=new int[strings.length];
            for(int i=0;i<strings.length;i++){
                numbers[i] = Integer.parseInt(strings[i]);
            }
            order1.setNumbers(numbers);
            //根据商品ID列查找商品
            List<Commodity> commodities=new ArrayList<>();
            String commodityIds = order1.getCommodityIds();
            String[] commodityIDs = commodityIds.split(",");
            for (String commodityID : commodityIDs) {
                int commodityId =Integer.valueOf(commodityID);
                Commodity commodity = commodityDao.selectOne(commodityId);
                commodities.add(commodity);
            }
            order1.setCommodityList(commodities);
        }

        //这里分页既不能用pagehleper，也不能用sql中的limit语句，所以只能用暴力算法来分页了
        //分页前的总数total
        Integer total = orders.size();
        //开始索引
        Integer beginIndex = (order.getPageable().get("pageNum") - 1) * order.getPageable().get("pageSize");
        //结束索引
        Integer endIndex = order.getPageable().get("pageNum") * order.getPageable().get("pageSize");
        //分页后的list
        //注意索引越界异常，因此执行subList方法前要校验结束索引是否大于总数，若大于则将总数赋值给结束索引
        if (endIndex > total){
            endIndex = total;
        }
        List<Order> orderList = orders.subList(beginIndex, endIndex);
        PageResult pageResult = new PageResult<Order>();
        pageResult.setTotal(total);
        pageResult.setList(orderList);
        return pageResult;
    }

    @Override
    public void create(Order order) {
        //商品ID列
        String commodityIds="";
        //购物车下单
        if (order.getCartIdList()!=null){
            int[] cartIdList = order.getCartIdList();
            for (int cartId : cartIdList) {
                Integer commodityId = cartDao.selectId(cartId);
                commodityIds=commodityIds+commodityId+",";
            }
            int i = commodityIds.lastIndexOf(",");
            commodityIds=commodityIds.substring(0,i);
            order.setCommodityIds(commodityIds);
        }else {
                //直接商品明细页下单
                String[] names = order.getNames();
                Commodity commodity = commodityDao.selectByName(names[0]);
                int commodityId = commodity.getId();
                commodityIds=commodityId+"";
                order.setCommodityIds(commodityIds);
        }
        //发货数量列
        int[] nums = order.getNums();
        String number="";
        for (int num : nums) {
            number=number+num+",";
        }
        int j=number.lastIndexOf(",");
        number=number.substring(0,j);
        order.setNumber(number);
        orderDao.create(order);
        //删除购物车
        if (order.getCartIdList()!=null){
            int[] cartIdList = order.getCartIdList();
            for (int cartId : cartIdList) {
                 cartDao.deleteOne(cartId);
            }
        }
    }
}
