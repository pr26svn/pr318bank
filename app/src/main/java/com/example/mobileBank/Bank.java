package com.example.mobileBank;

/**
 * Тип данных Банк, позволяющий хранить адрес, тип банка (отделение или банкомат),
 * статус работы (работает или закрыт) и часы работы
 */
public class Bank {
    private String address;
    private String type;
    private String working;
    private String workingHours;

    //конструктор
    public Bank(String address, String type, String working, String workingHours) {
        this.address = address;
        this.type = type;
        this.working = working;
        this.workingHours = workingHours;
    }

    //получение данных из класса
    public String getAddress() {
        return address;
    }
    public String getType() {
        return type;
    }
    public String getWorking() {
        return working;
    }
    public String getWorkingHours() {
        return workingHours;
    }
}
