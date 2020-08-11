package com.ph.springBoot.modules.test.service;

import com.github.pagehelper.PageInfo;
import com.ph.springBoot.modules.common.vo.SearchVo;
import com.ph.springBoot.modules.test.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesByCountryId(int countryId);

    PageInfo<City> getCitiesBySearchVo(int countryId,SearchVo searchVo);
}
