package com.example.mobilebank;

import android.media.Image;

public class Sales {
    private String usd;
    private String  usd_dollar;
    private String course_up;
    private String course_down;

    public Sales(String usd, String usd_dollar, String course_up, String course_down) {
        this.usd = usd;
        this.usd_dollar = usd_dollar;
        this.course_up = course_up;
        this.course_down = course_down;
    }

    public String getUsd() {
        return usd;
    }

    public String getUsd_dollar() {
        return usd_dollar;
    }

    public String getCourse_up() {
        return course_up;
    }

    public String getCourse_down() {
        return course_down;
    }
}
        /*int Flag;
        String Usd;
        String Usd_dollar;
        String Course_up;
        String Course_down;


        /* Конструктор */
        /*public Sales(int flag, String usd, String usd_dollar, String course_up, String course_down) {
            Flag = flag;
            Usd = usd;
            Usd_dollar = usd_dollar;
            Course_up = course_up;
            Course_down = course_down;
        }

        /* Getter Setter */
        /* public int getFlag() {
            return Flag;
        }

        public void setFlag(int flag) {
            Flag = flag;
        }

        public String getUsd() {
            return Usd;
        }

        public void setUsd(String usd) {
            Usd = usd;
        }

        public String getUsd_dollar() {
            return Usd_dollar;
        }

        public void setUsd_dollar(String usd_dollar) {
            Usd_dollar = usd_dollar;
        }

        public String getCourse_up() {
            return Course_up;
        }

        public void setCourse_up(String course_up) {
            Course_up = course_up;
        }

        public String getCourse_down() {
            return Course_down;
        }

        public void setCourse_down(String course_down) {
            Course_down = course_down;
        }

        public String toString() {
            return "Sales:" + Flag + " " + Usd + " " + Usd_dollar + " " + Course_up + " " + Course_down;
        }

        */





