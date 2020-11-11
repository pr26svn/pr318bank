package com.example.myapplication;

public class AtmsBuildings {
    String address;
    String name;
    String state;
    String time;

    public AtmsBuildings(String address, String name, String state, String time) {
        this.address = address;
        this.name = name;
        this.state = state;
        this.time = time;
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
