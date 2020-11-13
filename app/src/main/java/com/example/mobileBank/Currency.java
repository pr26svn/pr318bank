package com.example.mobileBank;

/**
 * Тип данных Валюта, позволяющий хранить полное и сокращенное название
 * валюты, курс покупки и продажи, флаг страны
 */
public class Currency {

    private int flag;
    private String charCode;
    private String name;
    private double buy;
    private double sell;

    // конструктор
    Currency(int flag, String charCode, String name, double buy, double sell) {
        this.flag = flag;
        this.charCode = charCode;
        this.name = name;
        this.buy = buy;
        this.sell = sell;
    }

    // получение данных из класса
    public int getFlag() {
        return flag;
    }
    public String getCharCode() {
        return charCode;
    }
    public String getName() {
        return name;
    }
    public double getBuy() {
        return buy;
    }
    public double getSell() {
        return sell;
    }
}
