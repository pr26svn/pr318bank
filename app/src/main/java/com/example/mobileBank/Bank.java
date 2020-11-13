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

    public Bank() {

    }
    //конструктор
    public Bank(String address, String type, String working, String workingHours) {
        this.address = address;
        this.type = type;
        this.working = working;
        this.workingHours = workingHours;
    }

    //получение данных из класса и запись данных в него
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getWorking() {
        return working;
    }
    public void setWorking(String working) {
        this.working = working;
    }

    public String getWorkingHours() {
        return workingHours;
    }
    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
