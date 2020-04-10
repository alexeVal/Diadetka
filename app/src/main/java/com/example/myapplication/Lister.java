package com.example.myapplication;

public class Lister {
    private String Time;
    private String Text;
    private long id;
    public Lister(String time, String text) {
        Time = time;
        Text = text;
    }

    public String getTime() {
        return Time;
    }

    public String getText() {
        return Text;
    }

    public long getId() {
        return id;
    }
}
