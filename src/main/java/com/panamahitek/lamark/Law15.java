package com.panamahitek.lamark;

public class Law15 {
    private UtilityCompany company;
    private int companyID;
    private int month;
    private int year;
    private double charge;
    private double subsidy;

    public Law15(UtilityCompany company, int month, int year, double charge, double subsidy) {
        this.company = company;
        this.month = month;
        this.year = year;
        this.charge = charge;
        this.subsidy = subsidy;
    }

    public Law15(int companyID, int month, int year, double charge, double subsidy) {
        this.companyID = companyID;
        this.month = month;
        this.year = year;
        this.charge = charge;
        this.subsidy = subsidy;
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

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(double subsidy) {
        this.subsidy = subsidy;
    }
    
}
