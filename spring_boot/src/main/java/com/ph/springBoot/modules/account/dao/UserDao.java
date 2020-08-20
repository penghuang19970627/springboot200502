package com.ph.springBoot.modules.account.dao;

import com.ph.springBoot.modules.account.entity.User;
import com.ph.springBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    /*插入数据*/
    @Insert("insert into user (user_name,user_password,create_date) values (#{userName},#{userPassword},#{createDate})")
    void insertUser(User user);

    /*判断用户名是否存在*/
    @Select("select * from user where user_name = #{userName}")
    User getUserByUserName(String userName);

    /*分页*/
    @Select("<script>" +
            "select * from user "
            + "<where> "
            + "<if test='keyWord != \"\" and keyWord != null'>"
            + " and (user_name like '%${keyWord}%') "
            + "</if>"
            + "</where>"
            + "<choose>"
            + "<when test='orderBy != \"\" and orderBy != null'>"
            + " order by ${orderBy} ${sort}"
            + "</when>"
            + "<otherwise>"
            + " order by user_id asc"
            + "</otherwise>"
            + "</choose>"
            + "</script>")
    List<User> getUserBySearchVo(SearchVo searchVo);
}
