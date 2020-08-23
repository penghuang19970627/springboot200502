package com.ph.springBoot.modules.account.dao;

import com.ph.springBoot.modules.account.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
@Mapper
public interface RoleDao {

    /*根据用户id查询角色*/
    @Select("select * from role r " +
            "left join user_role ur on r.role_id = ur.role_id" +
            " where ur.user_id = #{userId}")
    List<Role> getRolesByUserId(int userId);

    /*查询所有角色*/
    @Select("select * from role")
    List<Role> getRoles();
}
