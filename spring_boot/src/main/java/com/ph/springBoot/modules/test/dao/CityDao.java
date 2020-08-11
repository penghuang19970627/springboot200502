package com.ph.springBoot.modules.test.dao;

import com.ph.springBoot.modules.test.entity.City;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Mapper
public interface CityDao {
    @Select("select * from city where country_id=#{countryId}")
    List<City> getCitiesByCountryId(int countryId);
}
