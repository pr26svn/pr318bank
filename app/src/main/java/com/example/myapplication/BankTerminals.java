package com.example.myapplication;

public class BankTerminals {
    private String streetBank;
    private String workTime;

    public BankTerminals(String street, String workTime) {
        streetBank = street;
        workTime = workTime;
    }

    public String getBankData() {
        return streetBank + "\t" + workTime;
    }

}
