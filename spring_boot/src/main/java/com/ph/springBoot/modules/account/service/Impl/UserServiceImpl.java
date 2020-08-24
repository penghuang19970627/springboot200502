package com.ph.springBoot.modules.account.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ph.springBoot.config.ResourceConfigBean;
import com.ph.springBoot.modules.account.dao.UserDao;
import com.ph.springBoot.modules.account.dao.UserRoleDao;
import com.ph.springBoot.modules.account.entity.Role;
import com.ph.springBoot.modules.account.entity.User;
import com.ph.springBoot.modules.account.service.UserService;
import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private ResourceConfigBean resourceConfigBean;

    /*插入数据*/
    @Override
    @Transactional
    public Result<User> insertUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null){
            return new Result<User>(Result.ResultStatus.FAILD.status,"用户名已存在，请重新输入！");
        }
        String usertemp = user.getUserName();
        String userpassword = user.getPassword();
        System.out.println("————————————————————————"+usertemp+"_________________________"+userpassword+"——————————————————————————————————————————");
        if (usertemp != "" && userpassword != ""){
            System.err.println("————————————————————————"+usertemp+"_________________________"+userpassword+"——————————————————————————————————————————");
            user.setCreateDate(LocalDateTime.now());
            user.setPassword(MD5Util.getMD5(user.getPassword()));
            userDao.insertUser(user);
            userRoleDao.deleteUserRoleByUserId(user.getUserId());
            List<Role> roles = user.getRoles();
            if (roles != null && !roles.isEmpty()){
                roles.stream().forEach(item -> {
                    userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
                });
            }
            return new Result<User>(Result.ResultStatus.SUCCESS.status,"注册成功",user);
        }
        return new Result<User>(Result.ResultStatus.FAILD.status,"用户名或密码不能为空！！");
    }

    /*验证数据*/
    @Override
    public Result<User> selectUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getPassword().equals(MD5Util.getMD5(user.getPassword()))){
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

    /*修改*/
    @Override
    @Transactional
    public Result<User> updateUser(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()){
            return new Result<User>(Result.ResultStatus.FAILD.status,"此用户已存在，请重新修改");
        }
        userDao.updateUser(user);
        userRoleDao.deleteUserRoleByUserId(user.getUserId());
        List<Role> roles = user.getRoles();
        if (roles != null && !roles.isEmpty()){
            roles.stream().forEach(item -> {
                userRoleDao.insertUserRole(user.getUserId(),item.getRoleId());
            });
        }
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"修改成功",user);
    }

    /*删除*/
    @Override
    @Transactional
    public Result<Object> deleteUser(int userId) {
        userDao.deleteUser(userId);
        userRoleDao.deleteUserRoleByUserId(userId);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status,"删除成功");
    }

    @Override
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    @Override
    public Result<String> uploadUserImg(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "Please select img.");
        }

        String relativePath = "";
        String destFilePath = "";
        try {
            String osName = System.getProperty("os.name");
            if (osName.toLowerCase().startsWith("win")) {
                destFilePath = resourceConfigBean.getLocationPathForWindows() +
                        file.getOriginalFilename();
            } else {
                destFilePath = resourceConfigBean.getLocationPathForLinux()
                        + file.getOriginalFilename();
            }
            relativePath = resourceConfigBean.getRelativePath() +
                    file.getOriginalFilename();
            File destFile = new File(destFilePath);
            file.transferTo(destFile);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result<String>(
                    Result.ResultStatus.FAILD.status, "上传失败");
        }

        return new Result<String>(
                Result.ResultStatus.SUCCESS.status, "上传成功.", relativePath);
    }

    @Override
    @Transactional
    public Result<User> updateUserProfile(User user) {
        User userTemp = userDao.getUserByUserName(user.getUserName());
        if (userTemp != null && userTemp.getUserId() != user.getUserId()){
            return new Result<User>(Result.ResultStatus.FAILD.status,"此用户已存在，请重新修改");
        }
        userDao.updateUser(user);
        return new Result<User>(Result.ResultStatus.SUCCESS.status,"修改成功",user);
    }


}
