package com.ph.springBoot.modules.test.service;

import com.ph.springBoot.modules.test.entity.Country;

public interface CountryService {
    Country getCountryByCountryId(int countryId);

    Country getCountryByCountryName(String countryName);
}
