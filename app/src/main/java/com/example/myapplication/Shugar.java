package com.example.myapplication;

public class Shugar {
    private int id;
    private double level;
    private String time;

    public Shugar(double level,String time) {
        this.time = time;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
