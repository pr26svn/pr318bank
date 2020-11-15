package com.example.test;

public class Banks {
    //переменные
    private String mStreet;
    private String mWorkTime;
    boolean isworking=true;

    public Banks() {
        mStreet= "";
        mWorkTime = "";
    }
    public Banks(String street, String workTime) {
        mStreet = street;
        mWorkTime=workTime;
    }

    //геттеры и сеттеры для работы с данными
    public void setStreet(String street) {
        mStreet = street;
    }
    public String getStreet() {
        return mStreet;
    }

    public void setWorkTime(String workTime) {
        mWorkTime = workTime;
    }
    public String getWorkTime() {
        return mWorkTime;
    }

    public String getAllData() {
        return mStreet + "\t" + mWorkTime;
    }

}

