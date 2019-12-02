package com.aplication.CurrencyCalculator.model;

import com.aplication.CurrencyCalculator.model.Rate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Table {
    private String table;
    private String no;
    private String effectiveData;
    private ArrayList<Rate> rates;


    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEffectiveData() {
        return effectiveData;
    }

    public void setEffectiveData(String effectiveData) {
        this.effectiveData = effectiveData;
    }

    public ArrayList<Rate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<Rate> rates) {
        this.rates = rates;
    }


}
