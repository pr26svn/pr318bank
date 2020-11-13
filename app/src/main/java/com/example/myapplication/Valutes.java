package com.example.myapplication;

public class Valutes {
    private String valueCode;
    private String name;
    private String value;

    public Valutes(String valueCode, String name, String value) {
        this.valueCode = valueCode;
        this.name = name;
        this.value = value;
    }

    public String getValueCode() {
        return valueCode;
    }
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }
}
