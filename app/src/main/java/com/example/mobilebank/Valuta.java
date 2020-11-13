package com.example.mobilebank;

public class Valuta {
    /**
     * Класс Валюта
     * хранит в себе сокращенное и полное название валюты
     * а также id, стоимость купли и продажи
     * id сделал на всякий случай, в приложении он никак не используется,
     * но если буду развивать приложение дальше, он точно понадобится
     */
    String name, full_name;
    int id;
    double buy;
    int sell;


    //Конструктор
    Valuta(int ID, String Name, String Full_name, double Buy, int Sell){
        id = ID;
        name = Name;
        full_name = Full_name;
        buy = Buy;
        sell = Sell;
    }
}
