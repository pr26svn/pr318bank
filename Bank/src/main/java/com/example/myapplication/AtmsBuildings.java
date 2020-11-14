package com.example.myapplication;

/*
    Класс банкоматов и отделений, предназначенный для удобного использования данных
 */

public class AtmsBuildings {
    String address; // Адрес объекта
    String name; // Тип объекта
    String state; // Состояние объекта
    String time; // Время работы
    int color; // Цвет состояния

    // Конструктор класса
    public AtmsBuildings(String address, String name, String state, String time, int color) {
        this.address = address;
        this.name = name;
        this.state = state;
        this.time = time;
        this.color = color;
    }

    // Свойства полей класса
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
