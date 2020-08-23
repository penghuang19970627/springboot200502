package com.ph.springBoot.modules.account.dao;

import com.ph.springBoot.modules.account.entity.User;
import com.ph.springBoot.modules.common.vo.SearchVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    /*插入数据*/
    @Insert("insert into user (user_name,password,user_img,create_date) " +
            "values (#{userName},#{password},#{userImg},#{createDate})")
    @Options(useGeneratedKeys = true,keyColumn = "user_id",keyProperty = "userId")
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

    /*修改*/
    @Update("update user set user_name = #{userName},user_img = #{userImg} where user_id = #{userId}")
    void updateUser(User user);

    /*删除*/
    @Delete("delete from user where user_id = #{userId}")
    void deleteUser(int userId);

    /*user和role角色组合查询*/
    @Select("select * from user where user_id = #{userId}")
    @Results(id = "userResults" ,value = {
            @Result(column = "user_id",property = "userId"),
            @Result(column = "user_id",property = "roles",
                    javaType = List.class,
                    many = @Many(select = "com.ph.springBoot.modules.account.dao.RoleDao.getRolesByUserId"))
            }
            )
    User getUserByUserId(int UserId);

}
