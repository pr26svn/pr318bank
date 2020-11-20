package com.example.mobilebank;

public class a_t_m {
    String fullAddressRu;
    String TW;
    boolean works = false;
    a_t_m(String adress, String time_work, boolean Works) {
        fullAddressRu = adress;
        TW = time_work;
        works = Works;
    }

    @Override
    public String toString(){
        return "" + String.valueOf(fullAddressRu) + String.valueOf(TW) + works;
    }
}
