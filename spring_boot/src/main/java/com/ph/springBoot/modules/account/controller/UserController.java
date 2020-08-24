package com.ph.springBoot.modules.account.controller;

import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.account.entity.User;
import com.ph.springBoot.modules.account.service.UserService;
import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    /*修改*/
    /*127.0.0.1/ph/user*/
    /*{"userId":"8","userName":"luolang"}*/
    @PutMapping(value = "/user",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUser(@RequestBody User user) {
        return service.updateUser(user);
    }

    /*删除*/
    /*127.0.0.1/ph/user/2*/
    @DeleteMapping("/user/{userId}")
    public Result<Object> deleteUser(@PathVariable int userId){
        return service.deleteUser(userId);
    }

    //"127.0.0.1/ph/user/2"
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId){
        return service.getUserByUserId(userId);
    }

    /*127.0.0.1/ph/userImg*/
    @PostMapping(value = "/userImg", consumes = "multipart/form-data")
    public Result<String> uploadFile(@RequestParam MultipartFile file) {
        return service.uploadUserImg(file);
    }

    /*127.0.0.1/ph/profile*/
    @PutMapping(value = "/profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Result<User> updateUserProfile(@RequestBody User user){
        return service.updateUserProfile(user);
    }

}
