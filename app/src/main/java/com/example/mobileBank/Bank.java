package com.example.mobileBank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Тип данных Банк, позволяющий хранить адрес, тип банка (отделение или банкомат),
 * статус работы (работает или закрыт) и часы работы
 */
public class Bank {
    private String address;
    private String type;
    private boolean isWorking;
    private String workingHours;

    //конструктор
    public Bank(String address, String type, String workingHours) {
        this.address = address;
        this.type = type;
        this.workingHours = workingHours;
        // передача часов и минут в качестве массива
        setIsWorking(workingHours.split("-"));
    }

    //получение данных из класса и запись данных в него
    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }

    public boolean getIsWorking() {
        return isWorking;
    }

    // проверяет, работает ли банк в данный момент
    private void setIsWorking(String[] time) {
        // текущая дата и формат даты для сравнения
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        // проверка, работает ли банк круглосуточно
        boolean isWorkAroundTheClock = (time[0].equals("00:00") && time[1].equals("00:00"));

        // получение текущей даты
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy", Locale.getDefault());
        String currDate = dateFormat.format(date);

        // получение времени начала и конца рабочего дня типа Date
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = sdf.parse(currDate + " " + time[0]);
            endTime = sdf.parse(currDate + " " + time[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // присвоение переменной значения true, если банк работает, иначе - false
        isWorking = (!(date.before(startTime) || date.after(endTime)) || isWorkAroundTheClock);
    }

    public String getWorkingHours() {
        return workingHours;
    }
}
