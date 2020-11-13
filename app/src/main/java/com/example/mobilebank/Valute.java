package com.example.mobilebank;

import java.text.DecimalFormat;

public class Valute {
    String name;
    String full_name;
    String id;
    String Value;
    String sell;
    public static final String cours_format = "#0.00";

    Valute(String ID, String Name, String Full_name, String value, String Sell){
        id = ID;
        name = Name;
        full_name = Full_name;
        Value = value;
        sell = Sell;
    }
    public String ConvToDouble(String buy){
        double chislo = Double.parseDouble(buy);
        double result = chislo+(chislo*60);
        String otvet = Double.toString(result);
        return otvet;
    }
    public void SetName(String Name){
        name = Name;
    }
    public void SetFullName(String Full_name){
        full_name = Full_name;
    }
    public void SetValue(String value){
        String str = value.replace(',', '.');
        double chislo = Double.parseDouble(str);
        chislo = chislo+(chislo*1.5);
        String formattedDouble = new DecimalFormat(cours_format).format(chislo);
        Value = formattedDouble;
    }
    public void SetSell(String Sell){
        sell = Sell;
    }

}
