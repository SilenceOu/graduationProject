package com.example.shopping.service.impl;

import com.example.shopping.dao.AddressDao;
import com.example.shopping.dao.CartDao;
import com.example.shopping.dao.CommodityDao;
import com.example.shopping.dao.OrderDao;
import com.example.shopping.model.Address;
import com.example.shopping.model.Commodity;
import com.example.shopping.model.Order;
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
    public List<Order> search(Order order) {
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
        return list;
    }

    @Override
    public void updateStatus(Order order) {
        orderDao.updateStatus(order);
    }

    @Override
    public List<Order> searchByUser(Order order) {
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
        return orders;
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
