package com.ph.springcloud.springCloudClientTest.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.ph.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.ph.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import com.ph.springcloud.springCloudClientTest.modules.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchVo(int countryId, SearchVo searchVo);

    /*脚本多条件查询*/
    PageInfo<City> getCitiesBySearchVoMany(SearchVo searchVo);

    /*mybatis插入操作*/
    Result<City> insertCity(City city);

    /*mybatis修改操作*/
    Result<City> updateCity(City city);

    /*mybatis删除操作*/
    Result<Object> deleteCity(int cityId);
}
