package com.example.myapplication;

public class Food {
    private String name;
    private String url;
    private String xe;
    private int id;

    public Food(String name, String url, String xe) {
        this.name = name;
        this.url = url;
        this.xe = xe;
    }

    public Food(String name,String xe) {
        this.name = name;
        this.url = url;
        this.xe = xe;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getXe() {
        return xe;
    }

    public int getId() {
        return id;
    }
}
