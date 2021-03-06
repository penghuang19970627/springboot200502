package com.ph.springBoot.modules.account.service;

import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.account.entity.User;
import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    /*插入数据*/
    Result<User> insertUser(User user);

    /*验证用户名和密码*/
    Result<User> selectUser(User user);

    /*分页*/
    PageInfo<User> getUserBySearchVo(SearchVo searchVo);

    /*修改*/
    Result<User> updateUser(User user);

    /*删除*/
    Result<Object> deleteUser(int userId);

    User getUserByUserId(int userId);

    /*用户照片上传*/
    Result<String> uploadUserImg(MultipartFile file);

    Result<User> updateUserProfile(User user);

    User getUserByUserName(String userName);

    void logout();

}
