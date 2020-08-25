package com.ph.springBoot.modules.account.controller;


import com.ph.springBoot.modules.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService service;

    /*登录界面*/
    /*127.0.0.1/account/login*/
    @GetMapping("/login")
    public String loginPage(){
        return "indexSimple";
    }

    /*注册界面*/
    /*127.0.0.1/account/register*/
    @GetMapping("/register")
    public String registerPage(){
        return "indexSimple";
    }

    @GetMapping("/users")
    public String userPage(ModelMap map){
        return "index";
    }

    @GetMapping("/profile")
    public String profile(){
        return "index";
    }


    @GetMapping("/logout")
    public String logout(ModelMap modelMap) {
        service.logout();
        modelMap.addAttribute("template", "account/login");
        return "indexSimple";
    }

}
