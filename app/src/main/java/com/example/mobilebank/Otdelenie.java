package com.example.mobilebank;

public class Otdelenie {
    /**
     * Этот класс представляет собой Отделение
     * Поля буду выводить через собственный BoxAdapter в ListView
     */
    String fullAddressRu, tw;
    boolean isworking = false;


    //Конструктор
    Otdelenie(String adress, String time_work, boolean IsWorking) {
        fullAddressRu = adress;
        tw = time_work;
        isworking = IsWorking;
    }


    // этот метод я создавал для тестирования
    // метод выводит данные об объекте
    @Override
    public String toString(){
        return "[ Adress: " + String.valueOf(fullAddressRu) + ", Time: " + String.valueOf(tw) +" ] " + isworking;
    }
}
