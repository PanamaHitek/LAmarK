
package com.panamahitek.lamark;

import java.util.List;

public class FTO {
    private int semester;
    private int year;
    private List<Double> rates;

    public FTO(int semester, int year, List<Double> rates) {
        this.semester = semester;
        this.year = year;
        this.rates = rates;
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
