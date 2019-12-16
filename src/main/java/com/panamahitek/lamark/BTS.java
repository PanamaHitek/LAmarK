package com.panamahitek.lamark;

import java.util.List;

public class BTS {

    private UtilityCompany company;
    private int companyID;
    private int semester;
    private int year;
    private List<Double> rates;

    public BTS(UtilityCompany company, int semester, int year, List<Double> rates) {
        this.company = company;
        this.semester = semester;
        this.year = year;
        this.rates = rates;
    }

    public BTS(int companyID, int semester, int year, List<Double> rates) {
        this.companyID = companyID;
        this.semester = semester;
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

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
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
