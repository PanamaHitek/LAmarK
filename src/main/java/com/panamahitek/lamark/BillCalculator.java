package com.panamahitek.lamark;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class BillCalculator {

    private int utilityCompanyID;
    private UtilityCompany utilityCompany;
    private String utilityCompanyAcronym;

    private int month;
    private int year;
    private int semester;
    private int companyID;
    private int billedDays;
    private boolean retired;

    private int energy;
    private int btsInterval;

    private double subtotal;
    private double total;
    private double fetDiscount;
    private double additionalFetDiscount;
    private double ftoDiscount;
    private double retiredDiscount;
    private double law15Discount;
    private double law15Charge;
    private double fixedCharge;
    private double energyCharge;
    private double discounts;
    private double extraCharges;
    private double fuelCharge;

    private double fetRate;
    private double additionalFetRate;
    private double ftoRate;
    private double btsRate;
    private double cvcRate;
    private double fixedChargeRate;
    private double law15ChargeRate;
    private double law15SubsidyRate;

    private List<CVC> cvcRates;
    private List<BTS> btsRates;
    private List<FET> fetRates;
    private List<AdditionalFET> additionalFetRates;
    private List<FTO> ftoRates;
    private List<Law15> law15Rates;
    private List<FixedCharge> fixedChargeRates;
    private List<UtilityCompany> utilityCompanies;

    public BillCalculator() {
        loadRates();
    }

    public void setParameters(String utilityCompanyAcronym, int month, int year, int energy, int billedDays, boolean retired) {
        this.utilityCompanyAcronym = utilityCompanyAcronym;
        this.month = month;
        this.year = year;
        this.energy = energy;
        this.retired = retired;
        this.billedDays = billedDays;
        this.companyID = utilityCompanies
                .stream()
                .filter(a -> a.getCompanyAcronim().equals(utilityCompanyAcronym))
                .findFirst()
                .get()
                .getCompanyID();
    }

    public void setParameters(UtilityCompany utilityCompany, int month, int year, int energy, int billedDays, boolean retired) {
        this.utilityCompany = utilityCompany;
        this.month = month;
        this.year = year;
        this.energy = energy;
        this.billedDays = billedDays;
        loadRates();
        this.companyID = utilityCompany.getCompanyID();
    }

    public void setParameters(int utilityCompanyID, int month, int year, int energy, int billedDays, boolean retired) {
        this.utilityCompanyAcronym = utilityCompanyAcronym;
        this.month = month;
        this.year = year;
        this.energy = energy;
        this.billedDays = billedDays;
        loadRates();
        this.utilityCompanyID = utilityCompanyID;
    }

    private void loadRates() {
        PHP rates = new PHP();
        cvcRates = rates.loadCVC();
        btsRates = rates.loadBTS();
        fetRates = rates.loadFET();
        ftoRates = rates.loadFTO();
        law15Rates = rates.loadLaw15();
        fixedChargeRates = rates.loadFixedCharge();
        utilityCompanies = rates.loadUtilityCompanies();
        additionalFetRates = rates.loadAdditionalFET();
    }

    public void calculate() {

        DecimalFormat df = new DecimalFormat("#.##");

        if (month > 6) {
            semester = 2;
        } else {
            semester = 1;
        }

        int energyInterval = 0;

        if ((energy > 0) && (energy <= 50)) {
            energyInterval = 1;
        } else if ((energy > 50) && (energy <= 100)) {
            energyInterval = 2;
        } else if ((energy > 100) && (energy <= 150)) {
            energyInterval = 3;
        } else if ((energy > 150) && (energy <= 200)) {
            energyInterval = 4;
        } else if ((energy > 200) && (energy <= 250)) {
            energyInterval = 5;
        } else if ((energy > 250) && (energy <= 300)) {
            energyInterval = 6;
        } else if ((energy > 300) && (energy <= 350)) {
            energyInterval = 7;
        } else if ((energy > 350) && (energy <= 400)) {
            energyInterval = 8;
        } else if ((energy > 400) && (energy <= 450)) {
            energyInterval = 9;
        } else if ((energy > 450) && (energy <= 500)) {
            energyInterval = 10;
        } else if ((energy > 500) && (energy <= 600)) {
            energyInterval = 11;
        } else if ((energy > 600) && (energy <= 700)) {
            energyInterval = 12;
        } else if ((energy > 700) && (energy <= 750)) {
            energyInterval = 13;
        } else if ((energy > 750) && (energy <= 1000)) {
            energyInterval = 14;
        } else if ((energy > 1000) && (energy <= 1500)) {
            energyInterval = 15;
        } else if ((energy > 1500) && (energy <= 2000)) {
            energyInterval = 16;
        } else if ((energy > 2000) && (energy <= 2500)) {
            energyInterval = 17;
        } else if ((energy > 2500) && (energy <= 3000)) {
            energyInterval = 18;
        } else if ((energy > 3000)) {
            energyInterval = 19;
        }

        //Se establece el cargo fijo
        fixedChargeRate = fixedChargeRates
                .stream()
                .filter(a -> a.getCompanyID() == companyID)
                .filter(a -> a.getYear() == year)
                .filter(a -> a.getSemester() == semester)
                .findAny()
                .get()
                .getRate();

        //Se establece el Fondo Tarifarrio de Occidente
        if ((energyInterval - 1) > 15) {
            ftoRate = 0;
        } else {
            if (utilityCompanyID == 2) {
                ftoRate = ftoRates.stream()
                        .filter(a -> a.getYear() == year)
                        .filter(a -> a.getSemester() == semester)
                        .findAny()
                        .get()
                        .getRates()
                        .get(energyInterval - 1);
            } else {
                ftoRate = 0;
            }
        }

        //Se establece el tipo de tarifa
        if (energy <= 300 + ((billedDays - 30) * 10)) {
            btsInterval = 1;
        } else if (energy > (300 + ((billedDays - 310) * 10)) && (energy <= 750 + ((billedDays - 30) * 10))) {
            btsInterval = 2;
        } else if (energy > 750 + ((billedDays - 30) * 10)) {
            btsInterval = 3;
        }

        //Se establece el Fondo de Estabilizacion Tarifaria
        if (btsInterval == 1) {
            if ((energyInterval - 1) > 8) {
                fetRate = 0;
            } else {
                fetRate = fetRates.stream()
                        .filter(a -> a.getCompanyID() == companyID)
                        .filter(a -> a.getYear() == year)
                        .filter(a -> a.getMonth() == month)
                        .findAny()
                        .get()
                        .getRates()
                        .get(energyInterval - 1);
            }
        } else {
            fetRate = 0;
        }

        additionalFetRate = additionalFetRates.stream()
                .filter(a -> a.getCompanyID() == companyID)
                .filter(a -> a.getYear() == year)
                .filter(a -> a.getMonth() == month)
                .findAny()
                .get()
                .getRates()
                .get(energyInterval - 1);

        btsRate = btsRates.stream()
                .filter(a -> a.getCompanyID() == companyID)
                .filter(a -> a.getYear() == year)
                .filter(a -> a.getSemester() == semester)
                .findAny()
                .get()
                .getRates()
                .get(btsInterval - 1);

        //Se establece el CVC
        cvcRate = cvcRates.stream()
                .filter(a -> a.getCompanyID() == companyID)
                .filter(a -> a.getYear() == year)
                .filter(a -> a.getMonth() == month)
                .findAny()
                .get()
                .getRates()
                .get(btsInterval - 1);

        //Se establece el subsidio Ley 15
        if (energy <= 100 + ((billedDays - 30) * 10)) {
            law15SubsidyRate = law15Rates.stream()
                    .filter(a -> a.getCompanyID() == companyID)
                    .filter(a -> a.getYear() == year)
                    .filter(a -> a.getMonth() == month)
                    .findAny()
                    .get()
                    .getSubsidy();
        } else {
            law15SubsidyRate = 0;
        }

        //Se establece el cargo Ley 15
        if (energy >= 500 + ((billedDays - 30) * 10)) {
            law15ChargeRate = law15Rates.stream()
                    .filter(a -> a.getCompanyID() == companyID)
                    .filter(a -> a.getYear() == year)
                    .filter(a -> a.getMonth() == month)
                    .findAny()
                    .get()
                    .getCharge();
        } else {
            law15ChargeRate = 0;
        }

        fixedCharge = fixedChargeRate;
        energyCharge = (energy - 10) * btsRate;
        fuelCharge = cvcRate * energy;
        subtotal = fixedCharge + energyCharge + fuelCharge;

        additionalFetDiscount = additionalFetRate * subtotal * 0.01;
        ftoDiscount = ftoRate * subtotal * 0.01;
        fetDiscount = fetRate * subtotal * 0.01;
        law15Charge = law15ChargeRate * subtotal * 0.01;
        law15Discount = law15SubsidyRate * subtotal * 0.01;

        if (retired) {
            if (energy < 600) {
                retiredDiscount = subtotal - (subtotal * 0.25);
            }
        }

        discounts = ftoDiscount + fetDiscount + retiredDiscount + additionalFetDiscount + law15Discount;
        extraCharges = law15Charge;
        total = subtotal - discounts - law15Discount + law15Charge;

        /*
        System.out.println("-----------------------------");
        System.out.println("Cargos por consumo de energía");
        System.out.println("-----------------------------");
        System.out.println("Cargo fijo: " + fixedCharge);
        System.out.println("Cargo por energía: " + df.format(energyCharge));
        System.out.println("CVC - Costo de Variación por Combustible: " + df.format(fuelCharge));
        System.out.println("Subtotal: " + df.format(subtotal));
        System.out.println("-----------------------------");
        System.out.println("Subsitios y descuentos");
        System.out.println("-----------------------------");
        System.out.println("FTO - Fondo Tarifario de Occidente: " + df.format(ftoDiscount));
        System.out.println("FET - Fondo de Estabilización Tarifaria: " + df.format(fetDiscount));
        System.out.println("FET Adicional - Fondo de Estabilización Tarifaria Adicional (enero-junio 2019): " + df.format(additionalFetDiscount));
        System.out.println("Descuento de jubilados/tercera edad: " + df.format(retiredDiscount));
        System.out.println("Recargo subsidio (Ley 15 del 2001, cargo aplicable a clientes con consumo superior a 500 kWh/mes): " + df.format(law15Charge));
        System.out.println("Descuento Subsidio (Ley 15 del 2001, descuento aplicable a clientes con consumo igual o menor a 100 kWh): " + df.format(law15Discount));
        System.out.println("Total de subsidios y descuentos: " + df.format(discounts + law15Discount));
        System.out.println("-----------------------------");
        System.out.println("Total a pagar: " + df.format(total));
         */
    }

    public int getBtsInterval() {
        return btsInterval;
    }

    public void setBtsInterval(int btsInterval) {
        this.btsInterval = btsInterval;
    }

    public String getCompanyAcronym() {
        return utilityCompanies
                .stream()
                .filter(a -> a.getCompanyID() == companyID)
                .findFirst()
                .get()
                .getCompanyAcronim();
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    public double getFixedCharge() {
        return fixedCharge;
    }

    public void setFixedCharge(double fixedCharge) {
        this.fixedCharge = fixedCharge;
    }

    public double getBtsRate() {
        return btsRate;
    }

    public void setBtsRate(double btsRate) {
        this.btsRate = btsRate;
    }

    public double getCvcRate() {
        return cvcRate;
    }

    public void setCvcRate(double cvcRate) {
        this.cvcRate = cvcRate;
    }

    public double getEnergyCharge() {
        return energyCharge;
    }

    public void setEnergyCharge(double energyCharge) {
        this.energyCharge = energyCharge;
    }

    public double getFuelCharge() {
        return fuelCharge;
    }

    public void setFuelCharge(double fuelCharge) {
        this.fuelCharge = fuelCharge;
    }

    public double getLaw15Discount() {
        return law15Discount;
    }

    public void setLaw15Discount(double law15Discount) {
        this.law15Discount = law15Discount;
    }

    public double getLaw15Charge() {
        return law15Charge;
    }

    public void setLaw15Charge(double law15Charge) {
        this.law15Charge = law15Charge;
    }

    public double getFetRate() {
        return fetRate;
    }

    public void setFetRate(double fetRate) {
        this.fetRate = fetRate;
    }

    public double getFtoRate() {
        return ftoRate;
    }

    public void setFtoRate(double ftoRate) {
        this.ftoRate = ftoRate;
    }

    public double getLaw15ChargeRate() {
        return law15ChargeRate;
    }

    public void setLaw15ChargeRate(double law15ChargeRate) {
        this.law15ChargeRate = law15ChargeRate;
    }

    public double getLaw15SubsidyRate() {
        return law15SubsidyRate;
    }

    public void setLaw15SubsidyRate(double law15SubsidyRate) {
        this.law15SubsidyRate = law15SubsidyRate;
    }

    public double getFetDiscount() {
        return fetDiscount;
    }

    public void setFetDiscount(double fetDiscount) {
        this.fetDiscount = fetDiscount;
    }

    public double getFtoDiscount() {
        return ftoDiscount;
    }

    public void setFtoDiscount(double ftoDiscount) {
        this.ftoDiscount = ftoDiscount;
    }

    public double getRetiredDiscount() {
        return retiredDiscount;
    }

    public void setRetiredDiscount(double retiredDiscount) {
        this.retiredDiscount = retiredDiscount;
    }

    public double getExtraCharges() {
        return extraCharges;
    }

    public void setExtraCharges(double extraCharges) {
        this.extraCharges = extraCharges;
    }

}
