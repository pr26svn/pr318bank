package com.example.mobileBank;

/**
 * Тип данных Валюта, позволяющий хранить полное и сокращенное название
 * валюты, курс покупки и продажи
 */
public class Currency {
    private String name;
    private String fullName;
    private String buy;
    private String sell;

    //конструктор
    Currency(String name, String fullName, String buy, String sell) {
        this.name = name;
        this.fullName = fullName;
        this.buy = buy;
        this.sell = sell;
    }

    //получение данных из класса
    public String getName() {
        return name;
    }
    public String getFullName() {
        return fullName;
    }
    public String getBuy() {
        return buy;
    }
    public String getSell() {
        return sell;
    }
}
