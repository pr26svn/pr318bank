package com.example.mobilebank;

public class Otdelenie {
    /**
     * Этот класс представляет собой Отделение
     * Поля буду выводить через собственный BoxAdapter в ListView
     */
    String fullAddressRu, tw;


    //Конструктор
    Otdelenie(String adress, String time_work) {
        fullAddressRu = adress;
        tw = time_work;
    }


    // этот метод я создавал для тестирования
    // метод выводит данные об объекте
    @Override
    public String toString(){
        return "[ Adress: " + String.valueOf(fullAddressRu) + ", Time: " + String.valueOf(tw) +" ]";
    }
}
