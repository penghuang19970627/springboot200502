package com.ph.springBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.account.entity.User;
import com.ph.springBoot.modules.account.service.UserService;
import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/ph")
public class UserController {

    /*插入数据*/
    /*127.0.0.1/ph/user*/
    /*{"userName":"admin","userPassword":"123456"}*/
    @Autowired
    private UserService service;

    @PostMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> insertUser(@RequestBody User user){
        return service.insertUser(user);
    }

    /*验证数据*/
    /*127.0.0.1/ph/login*/
    /*{"userName":"penghuang","userPassword":"123456"}*/
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> selectUser(@RequestBody User user) {
        return service.selectUser(user);
    }


     /* 127.0.0.1/ph/users */
     /* {"currentPage":"1","pageSize":"5","keyWord":"pe"}*/
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<User> getUserBySearchVo(@RequestBody SearchVo searchVo) {
        return service.getUserBySearchVo(searchVo);
    }
}
