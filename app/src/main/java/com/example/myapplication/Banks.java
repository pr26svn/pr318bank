package com.example.myapplication;

public class Banks {
    private String mStreetAndState;
    private String mStatus;
    private String mWorkTime;

    public Banks() {
        mStreetAndState = "";
        mStatus = "";
        mWorkTime = "";
    }
    public Banks(String street, String state, String status, String workTime) {
        mStreetAndState = street + " " + state;
        mStatus = status;
        mWorkTime = workTime;
    }

    public void setStreetAndState(String street, String state) {
        mStreetAndState = street + " " + state;
    }
    public String getStreetAndState() {
        return mStreetAndState;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
    public String getStatus() {
        return mStatus;
    }

    public void setWorkTime(String workTime) {
        mWorkTime = workTime;
    }
    public String getWorkTime() {
        return mWorkTime;
    }

    public String getAllData() {
        return mStreetAndState + "\t" + mStatus + "\t" + mWorkTime;
    }

}
