package com.example.mobileBank;

public class Banks {
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
    }
}

