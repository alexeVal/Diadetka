package com.example.myapplication;

public class EatingFood  {
    private String xe;
    private String name;
    private String time;
    private int id;

    public EatingFood(String name,String xe,String time) {
        this.xe = xe;
        this.name = name;
        this.time = time;
    }

    public String getXe() {
        return xe;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
}
