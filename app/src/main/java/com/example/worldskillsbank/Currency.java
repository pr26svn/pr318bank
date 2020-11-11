package com.example.worldskillsbank;

public class Currency {
    private String name;
    private String littleName;
    private int nominal;
    private double price;
    private int numCode;

    public String getName() {
        return name;
    }

    public String getLittleName() {
        return littleName;
    }

    public int getNominal() {
        return nominal;
    }

    public double getPrice() {
        return price;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLittleName(String littleName) {
        this.littleName = littleName;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    String result(){
        return name + " - " + littleName + " - " + nominal + " - " + price;
    }
}
