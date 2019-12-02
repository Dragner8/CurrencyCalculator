package com.aplication.CurrencyCalculator.model;


import java.math.BigDecimal;

public class Rate {
    private String currency;
    private String code;
    private Float mid;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getMid() { return mid; }

    public void setMid(Float mid) {
        this.mid = mid;
    }

}
