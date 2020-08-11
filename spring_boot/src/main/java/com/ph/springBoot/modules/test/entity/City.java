package com.ph.springBoot.modules.test.entity;

public class City {
    private int cityId;
    private String cityName;
    private String cityPopulation;
    private Country country;
    private int countryId;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(String cityPopulation) {
        this.cityPopulation = cityPopulation;
    }
}
