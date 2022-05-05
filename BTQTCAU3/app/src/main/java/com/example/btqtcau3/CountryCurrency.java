package com.example.btqtcau3;

public class CountryCurrency {
    private String name;
    private String unit;
    private int flag;

    public CountryCurrency(String name, String unit, int flag) {
        this.name = name;
        this.unit = unit;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
