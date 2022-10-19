package com.example.shopping.service.impl;

import com.example.shopping.dao.ManagerDao;
import com.example.shopping.model.Manager;
import com.example.shopping.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Resource
    private ManagerDao managerDao;
    @Override
    public String login(Manager manager) {
        List<Manager> managerList=managerDao.findByUname(manager);
        if (managerList==null ||managerList.size()==0){
            return "用户名错误，请重新输入";
        }else {
            managerList=managerDao.findByUnameAndPwd(manager);
            if (managerList==null ||managerList.size()==0){
                return "密码错误，请重新输入";
            }else {
                return "登录成功";
            }
        }
    }

    @Override
    public String register(Manager manager) {
        List<Manager> managerList = managerDao.findByUname(manager);
        if (managerList==null ||managerList.size()==0){
            managerDao.insertOne(manager);
            return "注册成功";
        } else {
            return "该用户名已存在，请重新输入用户名";
        }
    }

    @Override
    public List<Manager> search(Manager manager) {
        return managerDao.search(manager);
    }

    @Override
    public Manager selectOne(Integer id) {
        return managerDao.selectOne(id);
    }

    @Override
    public String updateOne(Manager manager) {
        int count=managerDao.updateOne(manager);
        if (count>0){
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @Override
    public String deleteOne(Integer id) {
        int count=managerDao.deleteOne(id);
        if (count>0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }
}
