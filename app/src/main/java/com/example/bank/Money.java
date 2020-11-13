package com.example.bank;

public class Money {
    private String currency;
    private String abbreviation;
    private String buy;
    private String sell;
    //private String dollars;
    //private String euros;

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
    /*public String getDollars() {return dollars;}
    public String setDollars(String dollars) {return this.dollars = dollars;}
    public String getEuros() {return euros;}
    public String setEuros(String euros) {return this.euros = euros;}*/
}
