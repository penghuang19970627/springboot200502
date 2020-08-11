package com.ph.springBoot.modules.test.controller;

import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.modules.test.entity.City;
import com.ph.springBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ph")
public class CityController {

    @Autowired
    private CityService cityService;

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
}
