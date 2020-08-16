package com.ph.springBoot.modules.test.service.Impl;

import com.ph.springBoot.modules.test.dao.CountryDao;
import com.ph.springBoot.modules.test.entity.Country;
import com.ph.springBoot.modules.test.service.CountryService;
import com.ph.springBoot.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;
    /*@Autowired
    private RedisTemplate redisTemplate;*/
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Country getCountryByCountryId(int countryId) {
        return countryDao.getCountryByCountryId(countryId);
    }

    @Override
    public Country getCountryByCountryName(String countryName) {
        return countryDao.getCountryByCountryName(countryName);
    }

    @Override
    public Country managerCountryByRedis(int countryId) {
        Country country = countryDao.getCountryByCountryId(countryId);
        String countryKey = String.format("country%d",countryId);
        /*redisTemplate.opsForValue().set(countryKey,country);
        return (Country) redisTemplate.opsForValue().get(countryKey);*/
        redisUtils.set(countryKey,country);
        return (Country) redisUtils.get(countryKey);
    }


}
