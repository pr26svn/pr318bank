package com.example.myapplication;

import android.annotation.SuppressLint;
import android.renderscript.Sampler;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Course implements Serializable {// парсер-обьект
    private String numCode;
    private  String charCode;
    private String nominal;
    private String name;
    private Double value;
    private Double sell;

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value.toString();
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public String getCell(){

        double cell = value;
        cell *= 1.15;
        //String a = String.valueOf(cell);
        return String.format("%,4f", cell);
    }

    public String result(){
        return name + " - " + name + " - " + nominal + " - " + value;
    }
}
