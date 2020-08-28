package com.ph.springcloud.springCloudClientTest.modules.test.entity;

public class City {
    private int cityId;
    private String cityName;
    private String cityPopulation;
    private Country country;
    private int countryId;
    private String cityEmil;

    public String getCityEmil() {
        return cityEmil;
    }

    public void setCityEmil(String cityEmil) {
        this.cityEmil = cityEmil;
    }

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
