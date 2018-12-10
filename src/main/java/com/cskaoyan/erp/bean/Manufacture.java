package com.cskaoyan.erp.bean;

import java.util.Date;

public class Manufacture {
    private String manufactureSn;

    private Order cOrder;

    private Technology technology;

    private Integer launchQuantity;

    private Date beginDate;

    private Date endDate;

    public String getManufactureSn() {
        return manufactureSn;
    }

    public void setManufactureSn(String manufactureSn) {
        this.manufactureSn = manufactureSn == null ? null : manufactureSn.trim();
    }

    public Order getcOrder() {
        return cOrder;
    }

    public void setcOrder(Order cOrder) {
        this.cOrder = cOrder;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public Integer getLaunchQuantity() {
        return launchQuantity;
    }

    public void setLaunchQuantity(Integer launchQuantity) {
        this.launchQuantity = launchQuantity;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}