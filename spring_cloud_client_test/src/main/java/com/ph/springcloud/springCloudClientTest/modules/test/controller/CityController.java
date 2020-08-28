package com.ph.springcloud.springCloudClientTest.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.ph.springcloud.springCloudClientTest.modules.common.vo.Result;
import com.ph.springcloud.springCloudClientTest.modules.common.vo.SearchVo;
import com.ph.springcloud.springCloudClientTest.modules.test.entity.City;
import com.ph.springcloud.springCloudClientTest.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ph")
public class CityController {

    @Autowired
    private CityService cityService;

    /*127.0.0.1/ph/cities/1*/
    @GetMapping("/cities/{countryId}")
    public List<City> getCitiesByCountryId (@PathVariable int countryId){
        return cityService.getCitiesByCountryId(countryId);
    }

    /*127.0.0.1/ph/cities/1
    * {"currentPage":"1","pageSize":"5"}
    * */
    @PostMapping(value = "/cities/{countryId}",consumes = "application/json")
    public PageInfo<City> getCitiesBySearVo(@PathVariable int countryId,@RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchVo(countryId,searchVo);
    }


    /*脚本多条件查询*/
    /*{"currentPage":"1","pageSize":"5","keyWord":"55","orderBy":"city_name","sort":"desc"}*/
    @PostMapping(value = "/cities",consumes = "application/json")
    public PageInfo<City> getCitiesBySearchVoMany(@RequestBody SearchVo searchVo){
        return cityService.getCitiesBySearchVoMany(searchVo);
    }

    /*mybatis插入操作*/
    /*127.0.0.1/ph/city*/
    /*{"cityName":"BiJie","countryId","1","cityPopulation":"6000000","cityEmil":"551700"}*/
    @PostMapping(value = "/city" ,consumes = "application/json")
    public Result<City> insertCity(@RequestBody City city){
        return cityService.insertCity(city);
    }

    /*mybatis修改操作*/
    /*127.0.0.1/ph/city*/
    /*"cityId"="8","cityName"="QiXingGuan"*/
    @PutMapping(value = "/city" ,consumes = "application/x-www-form-urlencoded")
    public Result<City> updateCity(@ModelAttribute City city){
        return cityService.updateCity(city);
    }

    /*mybatis删除操作*/
    /*127.0.0.1/ph/city/11*/
    @DeleteMapping("/city/{cityId}")
    public Result<Object> deleteCity(@PathVariable int cityId){
        return cityService.deleteCity(cityId);
    }
}
