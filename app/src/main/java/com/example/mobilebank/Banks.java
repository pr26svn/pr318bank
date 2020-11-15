package com.example.mobilebank;

public class Banks {
    private String mStreet;
    private String mWorkTime;
    boolean Working = false;


    public Banks() {
        mStreet = "";
        mWorkTime = "";
    }

    public Banks(String street, String workTime) {
        mStreet = street;
        mWorkTime = workTime;
    }

    public void setStreet(String street) {
        mStreet = street;
    }
    public String getStreet() {
        return mStreet;
    }

    public void setWorkTime(String workTime) {
        mWorkTime = workTime;
    }
    public String getWorkTime() {
        return mWorkTime;
    }

    public String getAllData() {
        return mStreet + "\t" + mWorkTime;
    }

    /*
    String streets;
    String atm;
    String jobs;
    String hours;

    public Banks(String streets, String atm, String jobs, String hours) {
        this.streets = streets;
        this.atm = atm;
        this.jobs = jobs;
        this.hours = hours;
    }

    public String getStreets() {
        return streets;
    }

    public void setStreets(String streets) {
        this.streets = streets;
    }

    public String getAtm() {
        return atm;
    }

    public void setAtm(String atm) {
        this.atm = atm;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }*/
}
