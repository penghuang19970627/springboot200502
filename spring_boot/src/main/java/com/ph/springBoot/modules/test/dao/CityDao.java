package com.ph.springBoot.modules.test.dao;

import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Mapper
public interface CityDao {
    @Select("select * from city where country_id=#{countryId}")
    List<City> getCitiesByCountryId(int countryId);

    /*脚本的多条件查询*/
    @Select("<script>"+
            "select * from city "
            +"<where>"
            +"<if test='keyWord != \"\" and keyWord != null'>"
            +" and (city_name like '%${keyWord}%' or city_emil like '%${keyWord}%')"
            +"</if>"
            +"</where>"
            +"<choose>"
            +"<when test='orderBy != \"\" and orderBy != null'>"
            +" order by ${orderBy} ${sort}"
            +"</when>"
            +"<otherwise>"
            +" order by city_id desc"
            +"</otherwise>"
            +"</choose>"
            +"</script>")
    List<City> getCitiesBySearchVo(SearchVo searchVo);

    /*mybatis插入操作*/
    @Insert("insert into city (city_name,country_id,city_population,city_emil)" +
            " values (#{cityName},#{countryId},#{cityPopulation},#{cityEmil})")
    @Options(useGeneratedKeys = true,keyColumn = "city_id",keyProperty = "cityId")
    void insertCity(City city);

    /*mybatis修改操作*/
    @Update("update city set city_name = #{cityName} where city_id = #{cityId}")
    void updateCity(City city);

    /*mybatis删除操作*/
    @Delete("delete from city where city_id = #{cityId}")
    void deleteCity(int cityId);
}
