package com.ph.springcloud.springCloudClientAccount.modules.account.controller;


import com.ph.springcloud.springCloudClientAccount.modules.account.entity.User;
import com.ph.springcloud.springCloudClientAccount.modules.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ph")
public class UserController {


    @Autowired
    private UserService service;

    //"127.0.0.1/ph/user/2"
    @GetMapping("/user/{userId}")
    public User getUserByUserId(@PathVariable int userId){
        return service.getUserByUserId(userId);
    }



}
