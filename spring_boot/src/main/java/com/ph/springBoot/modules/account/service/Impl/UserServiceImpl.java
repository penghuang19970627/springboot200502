package com.ph.springBoot.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.account.dao.UserDao;
import com.ph.springBoot.modules.account.entity.User;
import com.ph.springBoot.modules.account.service.UserService;
import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /*插入数据*/
    @Override
    public Result<User> insertUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        String usertemp = user.getUserName();
        String userpassword = user.getUserPassword();
        System.out.println("————————————————————————"+usertemp+"_________________________"+userpassword+"——————————————————————————————————————————");
        if (!usertemp.equals(null) && !userpassword.equals(null)){
            user.setCreateDate(LocalDateTime.now());
            user.setUserPassword(MD5Util.getMD5(user.getUserPassword()));
            userDao.insertUser(user);
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"注册成功",user);
        }
        if (userTemp != null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"用户名已存在，请重新输入！");
        }
        return new Result<User>(Result.ResultStatus.FAILD.status,"用户名或密码不能为空！！");
    }

    /*验证数据*/
    @Override
    public Result<User> selectUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserPassword().equals(MD5Util.getMD5(user.getUserPassword()))){
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"登陆成功",user);
        }
        return new Result<User>(Result.ResultStatus.FAILD.status,"用户名或密码错误");
    }


    /*分页*/
    @Override
    public PageInfo<User> getUserBySearchVo(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
        return new PageInfo<User>(
                Optional.ofNullable(userDao.getUserBySearchVo(searchVo))
                        .orElse(Collections.emptyList()));
    }



}
