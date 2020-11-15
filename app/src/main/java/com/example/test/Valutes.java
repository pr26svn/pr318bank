package com.example.test;

public class Valutes {
    //переменные
    private String charCode;
    private String  name;
    private String value;

    //конструктор класса
    public Valutes(String charCode, String name, String value)
    {
        this.charCode=charCode;
        this.name=name;
        this.value=value;
    }

    //геттеры для работы с данными
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
