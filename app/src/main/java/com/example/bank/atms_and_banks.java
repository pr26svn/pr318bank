package com.example.bank;

import java.util.WeakHashMap;

public class atms_and_banks {

    String street;
    //String bank_and_atm;
    //String is_working;
    String work_hours;

    public atms_and_banks(String street, String work_hours) {
        this.street = street;
        //this.bank_and_atm = bank_and_atm;
        //this.is_working = is_working;
        this.work_hours = work_hours;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

   /* public String getBank_and_atm() {
        return bank_and_atm;
    }

    public void setBank_and_atm(String bank_and_atm) {
        this.bank_and_atm = bank_and_atm;
    }

    public String getIs_working() {
        return is_working;
    }

    public void setIs_working(String is_working) {
        this.is_working = is_working;
    }*/

    public String getWork_hours() {
        return work_hours;
    }

    public void setWork_hours(String work_hours) {
        this.work_hours = work_hours;
    }

}
