package com.ph.springBoot.modules.account.controller;

import com.ph.springBoot.modules.account.entity.Role;
import com.ph.springBoot.modules.account.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ph")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*127.0.0.1/ph/roles*/
    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
}
