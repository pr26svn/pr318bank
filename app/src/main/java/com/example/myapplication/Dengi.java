package com.example.myapplication;

//Объявление полей и организация доступа к ним с помощью свойств
public class Dengi {
    private String currency;
    private String abbreviation;
    private String buy;
    private String sell;

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
    public String getBuy(){
        return buy;
    }
    public String setBuy(String buy){
        return this.buy = buy;
    }
    public String getSell(){
        return sell;
    }
    public String setSell(String sell){
        return this.sell = sell;
    }
}
