package com.example.worldskillsbank;

// создание класса банкоматов и отделений
public class Bankomat {
    String address;
    String placeRu;
    String mon;

    public String getAddress() {
        return address;
    }

    public String getType() {
        return placeRu;
    }

    public String getMon() {
        return mon;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPlaceRu(String placeRu) {
        this.placeRu = placeRu;
    }

    public void setMon(String mon) {
        this.mon = mon;
    }
}
