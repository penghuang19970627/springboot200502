package com.ph.springBoot.modules.test.service;

import com.ph.springBoot.modules.test.entity.Country;

public interface CountryService {
    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);

    //Redis查询数据
    Country managerCountryByRedis(int countryId);
}
