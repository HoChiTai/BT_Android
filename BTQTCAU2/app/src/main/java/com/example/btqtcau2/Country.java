package com.example.btqtcau2;

public class Country {
    private String name;
    private String population;
    private String area;
    private String flag;
    private String capital;
    private String map;

    public Country(String name, String population, String area, String flag, String capital, String map) {
        this.name = name;
        this.population = population;
        this.area = area;
        this.flag = flag;
        this.capital = capital;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
}
