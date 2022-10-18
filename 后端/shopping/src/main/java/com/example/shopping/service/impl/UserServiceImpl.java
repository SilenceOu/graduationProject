package com.example.shopping.service.impl;

import com.example.shopping.dao.AddressDao;
import com.example.shopping.dao.CartDao;
import com.example.shopping.dao.UserDao;
import com.example.shopping.model.*;
import com.example.shopping.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private CartDao cartDao;
    @Resource
    private AddressDao addressDao;

    @Override
    public LoginMessage login(User user) {
        List<User> userList = userDao.findByUname(user);
        LoginMessage loginMessage=new LoginMessage();
        if (userList==null ||userList.size()==0){
            loginMessage.setMessage("用户名错误，请重新输入");
            return loginMessage;
        }else {
            userList=userDao.findByUnameAndPwd(user);
            if (userList==null ||userList.size()==0){
                loginMessage.setMessage("密码错误，请重新输入");
                return loginMessage;
            }else {
                loginMessage.setUserList(userList);
                loginMessage.setMessage("登录成功");
                return loginMessage;
            }
        }
    }

    @Override
    public String register(User user) {
        List<User> userList = userDao.findByUname(user);
        if (userList==null ||userList.size()==0){
            userDao.insertOne(user);
            return "注册成功";
        } else {
            return "该用户名已存在，请重新输入用户名";
        }
    }

    @Override
    public List<User> search(User user) {
        return userDao.search(user);
    }

    @Override
    public User selectOne(Integer id) {
        return userDao.selectOne(id);
    }

    @Override
    public String updateOne(User user) {
        int count=userDao.updateOne(user);
        if (count>0){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @Override
    public String deleteOne(Integer id) {
        int count=userDao.deleteOne(id);
        if (count>0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }

    //获取用户下的购物车
    @Override
    public List<Cart> getCart(Integer userId) {
        List<Cart> carts = cartDao.selectCarts(userId);
        for (Cart cart : carts) {
            int commodityId = cart.getCommodityId();
            Commodity commodity = cartDao.selectCommodity(commodityId);
            cart.setName(commodity.getName());
            cart.setMoney(commodity.getMoney());
            cart.setImage(commodity.getImage());
        }
        return carts;
    }
    //修改商品数量改变商品总价
    @Override
    public void updateCart(Cart cart) {
        cartDao.updateAmount(cart);
    }
    //加入购物车
    @Override
    public String saveCart(Cart cart) {
        //根据商品名称去商品表里查商品ID
        Integer commodityId = cartDao.selectCommodityId(cart);
        cart.setCommodityId(commodityId);
        //根据商品ID和用户ID去查找购物车
        Cart c = cartDao.selectCartByCondition(cart);
        //判断用户购物车里是否存在该商品
        if (c !=null){
            //若存在，则更新，否则新增
            int number=c.getNumber()+cart.getNumber();
            int amount=c.getAmount()+cart.getAmount();
            c.setNumber(number);
            c.setAmount(amount);
            cartDao.updateCart(c);
        } else {
            cartDao.insertCart(cart);
        }
        return "加入购物车成功！";
    }

    @Override
    public void deleteCart(Integer id) {
        cartDao.deleteOne(id);
    }

    @Override
    public List<Address> getAddress(Address address) {
        return addressDao.search(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressDao.updateOne(address);
    }

    @Override
    public void saveAddress(Address address) {
        addressDao.insertOne(address);
    }

    @Override
    public void deleteAddress(Integer id) {
        addressDao.deleteOne(id);
    }
}
