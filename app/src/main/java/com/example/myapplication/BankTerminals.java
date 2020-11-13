package com.example.myapplication;

public class BankTerminals {
    private String streetBank; // <- улица банка
    private String workTime; // <- время работы банка

    // конструктор, инициализирующий улици и время работы
    public BankTerminals(String street, String workTime) {
        streetBank = street;
        workTime = workTime;
    }

    // вывод данных о банке
    public String getBankData() {
        return streetBank + "\t" + workTime;
    }

}
