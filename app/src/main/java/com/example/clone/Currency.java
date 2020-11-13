package com.example.clone;

public class Currency {
    String name;
    String nominal;
    String numCode;
    String charCode;
    String value;

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    int Image;

    public Currency(int image, String name, String charCode, String value) {
        this.Image = image;
        this.name = name;
        this.charCode = charCode;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString(){
        return name + "\n" + nominal + "\n" + numCode  + "\n" + charCode + "\n" + value + "\n";
    }
}
