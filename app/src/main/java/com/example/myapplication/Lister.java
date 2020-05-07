package com.example.myapplication;

public class Lister {

    private String Time;
    private String Text;
    private int isred;
    private long id;

    public Lister(String time, String text, int isred) {
        this.Time = time;
        this.Text = text;
        this.isred= isred;
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

    public int getIsred() {
        return isred;
    }
}
