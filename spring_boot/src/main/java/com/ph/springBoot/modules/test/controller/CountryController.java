package com.ph.springBoot.modules.test.controller;

import com.ph.springBoot.modules.test.entity.Country;
import com.ph.springBoot.modules.test.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ph")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/country/{countryId}")
    public Country getCountryByCountryId(@PathVariable int countryId){
        return countryService.getCountryByCountryId(countryId);
    }

    /*127.0.0.1/ph/country?countryName=China*/
    @GetMapping("/country")
    public Country getCountryByCountryName(@RequestParam String countryName){
        return countryService.getCountryByCountryName(countryName);
    }
}
