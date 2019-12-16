package com.panamahitek.lamark;

import java.util.List;

public class FET {

    private UtilityCompany company;
    private int companyID;
    private int month;
    private int year;
    private List<Double> rates;

    public FET(UtilityCompany company, int month, int year, List<Double> rates) {
        this.company = company;
        this.month = month;
        this.year = year;
        this.rates = rates;
    }

    public FET(int companyID, int month, int year, List<Double> rates) {
        this.companyID = companyID;
        this.month = month;
        this.year = year;
        this.rates = rates;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public UtilityCompany getCompany() {
        return company;
    }

    public void setCompany(UtilityCompany company) {
        this.company = company;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Double> getRates() {
        return rates;
    }

    public void setRates(List<Double> rates) {
        this.rates = rates;
    }

}
