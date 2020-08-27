package com.ph.springBoot.modules.account.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Excelpoi {
    @cn.afterturn.easypoi.excel.annotation.Excel(name = "用户Id", orderNum = "0")
    private int  id;
    @cn.afterturn.easypoi.excel.annotation.Excel(name = "用户名", orderNum = "1")
    private String userName;
    @cn.afterturn.easypoi.excel.annotation.Excel(name = "密码", orderNum = "2")
    private String password;
    @cn.afterturn.easypoi.excel.annotation.Excel(name = "姓名", orderNum = "3")
    private String realName;
    @cn.afterturn.easypoi.excel.annotation.Excel(name = "电话", orderNum = "4")
    private String tel;
    @cn.afterturn.easypoi.excel.annotation.Excel(name = "年龄", orderNum = "5")
    private int age;
    @cn.afterturn.easypoi.excel.annotation.Excel(name = "地址", orderNum = "6")
    private String address;

    public Excelpoi() {
    }

    public Excelpoi(int id, String userName, String password, String realName, String tel, int age, String address) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.realName = realName;
        this.tel = tel;
        this.age = age;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Excelpoi{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", tel='" + tel + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
