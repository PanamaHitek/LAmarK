package com.panamahitek.lamark;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PHP {

    private static final String webURL = "http://localhost/bill_calculator";

    private String getURL(String url) {
        try {
            URL yahoo = new URL(url);
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            yc.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            return inputLine;
        } catch (MalformedURLException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<UtilityCompany> loadUtilityCompanies() {
        List<UtilityCompany> pList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getURL(webURL + "/loadData.php?table_name=distribution_companies"));
            JSONArray array1 = (JSONArray) obj;
            for (int i = 0; i < array1.size(); i++) {
                JSONObject obj2 = (JSONObject) array1.get(i);
                pList.add(new UtilityCompany(
                        Integer.parseInt(String.valueOf(obj2.get("id"))),
                        String.valueOf(obj2.get("name")),
                        String.valueOf(obj2.get("acronym"))));
            }
            return pList;
        } catch (ParseException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Law15> loadLaw15() {
        List<Law15> pList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getURL(webURL + "/loadData.php?table_name=law_15"));
            JSONArray array1 = (JSONArray) obj;
            for (int i = 0; i < array1.size(); i++) {
                JSONObject obj2 = (JSONObject) array1.get(i);
                pList.add(new Law15(
                        Integer.parseInt(String.valueOf(obj2.get("company_id"))),
                        Integer.parseInt(String.valueOf(obj2.get("month"))),
                        Integer.parseInt(String.valueOf(obj2.get("year"))),
                        Double.parseDouble(String.valueOf(obj2.get("charge"))),
                        Double.parseDouble(String.valueOf(obj2.get("subsidy")))
                ));
            }
            return pList;
        } catch (ParseException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<FTO> loadFTO() {
        List<FTO> pList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getURL(webURL + "/loadData.php?table_name=fto"));
            JSONArray array1 = (JSONArray) obj;
            for (int i = 0; i < array1.size(); i++) {
                JSONObject obj2 = (JSONObject) array1.get(i);

                JSONParser p = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(String.valueOf(obj2.get("rates")));
                List<Double> rates = new ArrayList<>();
                for (int j = 0; j < json.size(); j++) {
                    rates.add(Double.parseDouble(String.valueOf(json.get("I" + (i + 1)))));
                }
                pList.add(new FTO(
                        Integer.parseInt(String.valueOf(obj2.get("semester"))),
                        Integer.parseInt(String.valueOf(obj2.get("year"))),
                        rates
                ));

            }
            return pList;
        } catch (ParseException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<FET> loadFET() {
        List<FET> pList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getURL(webURL + "/loadData.php?table_name=fet"));
            JSONArray array1 = (JSONArray) obj;
            for (int i = 0; i < array1.size(); i++) {
                JSONObject obj2 = (JSONObject) array1.get(i);

                JSONParser p = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(String.valueOf(obj2.get("rates")));
                List<Double> rates = new ArrayList<>();
                for (int j = 0; j < json.size(); j++) {
                    rates.add(Double.parseDouble(String.valueOf(json.get("I" + (j + 1)))));
                }
                pList.add(new FET(
                        Integer.parseInt(String.valueOf(obj2.get("company_id"))),
                        Integer.parseInt(String.valueOf(obj2.get("month"))),
                        Integer.parseInt(String.valueOf(obj2.get("year"))),
                        rates
                ));

            }
            return pList;
        } catch (ParseException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<AdditionalFET> loadAdditionalFET() {
        List<AdditionalFET> pList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getURL(webURL + "/loadData.php?table_name=additional_fet"));
            JSONArray array1 = (JSONArray) obj;
            for (int i = 0; i < array1.size(); i++) {
                JSONObject obj2 = (JSONObject) array1.get(i);
                          JSONParser p = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(String.valueOf(obj2.get("rates")).toString());
                List<Double> rates = new ArrayList<>();
                for (int j = 0; j < json.size(); j++) {
                    rates.add(Double.parseDouble(String.valueOf(json.get("I" + (j + 1)))));
                }
                pList.add(new AdditionalFET(
                        Integer.parseInt(String.valueOf(obj2.get("company_id"))),
                        Integer.parseInt(String.valueOf(obj2.get("month"))),
                        Integer.parseInt(String.valueOf(obj2.get("year"))),
                        rates
                ));

            }
            return pList;
        } catch (ParseException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<CVC> loadCVC() {
        List<CVC> pList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getURL(webURL + "/loadData.php?table_name=cvc"));
            JSONArray array1 = (JSONArray) obj;
            for (int i = 0; i < array1.size(); i++) {
                JSONObject obj2 = (JSONObject) array1.get(i);

                JSONParser p = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(String.valueOf(obj2.get("rates")));
                List<Double> rates = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    rates.add(Double.parseDouble(String.valueOf(json.get("BTS" + (j + 1)))));
                }
                pList.add(new CVC(
                        Integer.parseInt(String.valueOf(obj2.get("company_id"))),
                        Integer.parseInt(String.valueOf(obj2.get("month"))),
                        Integer.parseInt(String.valueOf(obj2.get("year"))),
                        rates
                ));

            }
            return pList;
        } catch (ParseException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<BTS> loadBTS() {
        List<BTS> pList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getURL(webURL + "/loadData.php?table_name=bts"));
            JSONArray array1 = (JSONArray) obj;
            for (int i = 0; i < array1.size(); i++) {
                JSONObject obj2 = (JSONObject) array1.get(i);

                JSONParser p = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(String.valueOf(obj2.get("rates")));
                List<Double> rates = new ArrayList<>();
                for (int j = 0; j < json.size(); j++) {
                    rates.add(Double.parseDouble(String.valueOf(json.get("BTS" + (j + 1)))));
                }
                pList.add(new BTS(
                        Integer.parseInt(String.valueOf(obj2.get("company_id"))),
                        Integer.parseInt(String.valueOf(obj2.get("semester"))),
                        Integer.parseInt(String.valueOf(obj2.get("year"))),
                        rates
                ));
            }
            return pList;
        } catch (ParseException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<FixedCharge> loadFixedCharge() {
        List<FixedCharge> pList = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(getURL(webURL + "/loadData.php?table_name=fixed_charge"));
            JSONArray array1 = (JSONArray) obj;
            for (int i = 0; i < array1.size(); i++) {
                JSONObject obj2 = (JSONObject) array1.get(i);
                pList.add(new FixedCharge(
                        Integer.parseInt(String.valueOf(obj2.get("company_id"))),
                        Integer.parseInt(String.valueOf(obj2.get("semester"))),
                        Integer.parseInt(String.valueOf(obj2.get("year"))),
                        Double.parseDouble(String.valueOf(obj2.get("rate")))
                ));
            }
            return pList;
        } catch (ParseException ex) {
            Logger.getLogger(PHP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
