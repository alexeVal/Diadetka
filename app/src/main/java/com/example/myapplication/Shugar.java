package com.example.myapplication;

public class Shugar {   // класс описывающий уровень сахара
    private  int id;
    private  String time;
    private  double lelel;

    public Shugar(double lelel,String time) {
        this.time = time;
        this.lelel = lelel;
    }

    public int getId() {
        return id;
    }

    public double getLevel() {
        return lelel;
    }

    public void setLevel(double lelel) {
        this.lelel = lelel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
