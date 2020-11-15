package com.example.myapplication;

public class Valutes {
    private String charCode;
    private String  name;
    private String value;

    public Valutes(String charCode, String name, String value)
    {
        this.charCode=charCode;
        this.name=name;
        this.value=value;
    }

    public String getCharCode()
    {
        return charCode;
    }
    public  String getName(){
        return  name;
    }
    public  String getValue(){
        return  value;
    }
}
