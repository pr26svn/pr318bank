package com.example.worldskillsbank;

import java.io.Serializable;

// создание класса валют
public class Currency implements Serializable {
    private String name;
    private String littleName;
    private String price;

    public String getName() {
        return name;
    }

    public String getLittleName() {
        return littleName;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLittleName(String littleName) {
        this.littleName = littleName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCell(){
        price.replace(',', '.');
        double cell = Double.parseDouble(price);
        cell *= 1.15;
        return String.valueOf(cell);
    }
}
