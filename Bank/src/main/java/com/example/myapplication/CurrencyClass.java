package com.example.myapplication;

/*
    Класс валют, предназначенный для удобного использования данных
 */
public class CurrencyClass {
    int flag; // Флаг валюты
    String  currency; // Код валюты
    String name; // Название валюты
    String buy; // Курс покупки валюты
    String sell; // Курс продажи валюты

    // Конструктор класса
    public CurrencyClass(int flag, String currency, String name, String buy, String sell) {
        this.flag = flag;
        this.currency = currency;
        this.name = name;
        this.buy = buy;
        this.sell = sell;
    }

    // Свойства полей класса
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }
}
