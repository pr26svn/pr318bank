package com.example.myapplication;

public class ValutResourse {
    //переменные
    private String charCode;
    private String  name;
    private String value;

    //конструктор класса
    public ValutResourse(String charCode, String name, String value)
    {
        this.charCode=charCode;
        this.name=name;
        this.value=value;
    }
    //возврат данных
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
