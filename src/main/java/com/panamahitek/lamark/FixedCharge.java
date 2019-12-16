package com.panamahitek.lamark;

public class FixedCharge {

    private UtilityCompany company;
    private int companyID;
    private int semester;
    private int year;
    private double rate;

    public FixedCharge(UtilityCompany company, int semester, int year, double rate) {
        this.company = company;
        this.semester = semester;
        this.year = year;
        this.rate = rate;
    }

    public FixedCharge(int companyID, int semester, int year, double rate) {
        this.companyID = companyID;
        this.semester = semester;
        this.year = year;
        this.rate = rate;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
