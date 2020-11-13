package com.example.myapplication;

public class Valuta {
    String name, full_name, id;
    String buy, sell;



    Valuta(String ID, String Name, String Full_name, String Buy, String Sell){
        id = ID;
        name = Name;
        full_name = Full_name;
        buy = Buy;
        sell = Sell;
    }

    public void SetName(String Name){
        name = Name;
    }

    public void SetFullName(String Full_name){
        full_name = Full_name;
    }

    public void SetBuy(String Buy){
        buy = Buy;
    }

    public void SetSell(String Sell){
        sell = Sell;
    }
}
