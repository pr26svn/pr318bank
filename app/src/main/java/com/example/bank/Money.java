package com.example.bank;

public class Money {
    private String currency;
    private String abbreviation;
    private double buy;
    private double sell;

    public String getCurrency(){
        return currency;
    }
    public String setCurrency(String currency){
        return this.currency = currency;
    }
    public String getAbbreviation(){
        return abbreviation;
    }
    public String setAbbreviation(String abbreviation){
        return this.abbreviation = abbreviation;
    }
    public double getBuy(){
        return buy;
    }
    public double setBuy(double buy){
        return this.buy = buy;
    }
    public double getSell(){
        return sell;
    }
    public double setSell(double sell){
        return this.sell = sell;
    }
}
