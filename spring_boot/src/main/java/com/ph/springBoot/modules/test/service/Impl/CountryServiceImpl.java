package com.ph.springBoot.modules.test.service.Impl;

import com.ph.springBoot.modules.test.dao.CountryDao;
import com.ph.springBoot.modules.test.entity.Country;
import com.ph.springBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;
    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }


}
