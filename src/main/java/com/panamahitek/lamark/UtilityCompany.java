package com.panamahitek.lamark;

public class UtilityCompany {
    private int companyID;
    private String companyName;
    private String companyAcronim;

    public UtilityCompany(int companyID, String companyName, String companyAcronim) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.companyAcronim = companyAcronim;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAcronim() {
        return companyAcronim;
    }

    public void setCompanyAcronim(String companyAcronim) {
        this.companyAcronim = companyAcronim;
    }
    
    
}
