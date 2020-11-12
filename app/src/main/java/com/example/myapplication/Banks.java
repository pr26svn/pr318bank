package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;

public class Banks {

    private String Street;
    private String TimeWork;

    public Banks(String street,String timeWork){
        this.Street=street;
        this.TimeWork=timeWork;
    }
    public String returnStreet(){
        return Street;
    }
    public String returntimeWork(){
        return TimeWork;
    }

}
