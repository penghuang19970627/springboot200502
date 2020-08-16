package com.ph.springBoot.modules.test.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.common.vo.Result;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.modules.test.dao.CityDao;
import com.ph.springBoot.modules.test.entity.City;
import com.ph.springBoot.modules.test.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCitiesByCountryId(int countryId) {
        return Optional
                .ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList());
    }

    @Override
    public PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional
                .ofNullable(cityDao.getCitiesByCountryId(countryId))
                .orElse(Collections.emptyList()));
    }

    @Override
    public PageInfo<City> getCitiesBySearchVoMany(SearchVo searchVo) {
        searchVo.initSearchVo();
        PageHelper.startPage(searchVo.getCurrentPage(),searchVo.getPageSize());
        return new PageInfo<City>(Optional
                .ofNullable(cityDao.getCitiesBySearchVo(searchVo))
                .orElse(Collections.emptyList()));
    }


    /*mybatis插入操作*/
    @Override
    @Transactional
    public Result<City> insertCity(City city) {
        cityDao.insertCity(city);
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"Insert success.",city);
    }


    /*mybatis修改操作*/
    @Override
    @Transactional
    //(noRollbackFor = ArithmeticException.class)
    public Result<City> updateCity(City city) {
        cityDao.updateCity(city);
        //int i = 1 / 0;
        return new Result<City>(Result.ResultStatus.SUCCESS.status,"Update success.",city);
    }

    /*mybatis删除操作*/
    @Override
    @Transactional
    public Result<Object> deleteCity(int cityId) {
        cityDao.deleteCity(cityId);
        return new Result<Object>(Result.ResultStatus.SUCCESS.status,"Delete success.");
    }


}
