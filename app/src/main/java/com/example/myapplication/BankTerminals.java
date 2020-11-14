package com.example.myapplication;

public class BankTerminals {
    private String streetBank; // <- улица банка
    private String workTime; // <- время работы банка
    private boolean isWork = false;

    // конструктор, инициализирующий улици и время работы
    public BankTerminals(String street, String workTime) {
        streetBank = street;
        workTime = workTime;
    }

    public String getBankStreet() {
        return streetBank;
    }
    public String getWorkTime() {
        return workTime;
    }
    public boolean getIsWork() {
        return isWork;
    }

}
