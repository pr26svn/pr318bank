package com.example.mobilebank;

public class a_t_m {
    String fullAddressRu;
    String TW;

    a_t_m(String adress, String time_work) {
        fullAddressRu = adress;
        TW = time_work;
    }

    @Override
    public String toString(){
        return "Adress: " + String.valueOf(fullAddressRu) + ", Time: " + String.valueOf(TW);
    }
}
