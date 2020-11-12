package com.example.mobilebank;

public class Otdelenie {
    String fullAddressRu, tw;

    Otdelenie(String adress, String time_work) {
        fullAddressRu = adress;
        tw = time_work;
    }

    @Override
    public String toString(){
        return "[ Adress: " + String.valueOf(fullAddressRu) + ", Time: " + String.valueOf(tw) +" ]";
    }
}
