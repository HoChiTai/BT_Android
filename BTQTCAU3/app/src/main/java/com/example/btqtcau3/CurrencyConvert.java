package com.example.btqtcau3;

public class CurrencyConvert {
    private String currencyFirst;
    private String currencyLast;

    public CurrencyConvert(String currencyFirst, String currencyLast) {
        this.currencyFirst = currencyFirst;
        this.currencyLast = currencyLast;
    }

    public String getCurrencyFirst() {
        return currencyFirst;
    }

    public void setCurrencyFirst(String currencyFirst) {
        this.currencyFirst = currencyFirst;
    }

    public String getCurrencyLast() {
        return currencyLast;
    }

    public void setCurrencyLast(String currencyLast) {
        this.currencyLast = currencyLast;
    }
}
