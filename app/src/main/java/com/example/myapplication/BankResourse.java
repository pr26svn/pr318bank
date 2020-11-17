package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;

public class BankResourse {
    //перменные
    private String mStreet;
    private String mWorkTime;
    boolean isworking=false;

    public BankResourse() {
        mStreet= "";
        mWorkTime = "";
    }
    public BankResourse(String street, String workTime) {
        mStreet = street;
        mWorkTime=workTime;
    }

    //возврат данных
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
