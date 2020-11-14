package com.example.clone;

public class MainBancomats {
    String street;
    String time;
    String status;
    String view;

    public MainBancomats(String street, String time, String status, String view) {
        this.street = street;
        this.time = time;
        this.status = status;
        this.view = view;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
