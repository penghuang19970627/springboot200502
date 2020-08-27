package com.ph.springBoot.modules.account.dao;

import com.ph.springBoot.modules.account.entity.Excelpoi;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExcelpoiDao {

    @Select("select * from t_user")
    List<Excelpoi> getExportDatas();

    @Insert("insert into t_user(user_name,password,real_name,tel,age,address) " +
            "values(#{userName},#{password},#{realName},#{tel},#{age},#{address})")
    void saveImport(Excelpoi userModel);

    @Select("select * from t_user where user_name = #{userName}")
    Excelpoi getExcelpoiByUserName(String userName);
}
