package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("name")
    private String name;
    @SerializedName("par1")
    private String url;
    @SerializedName("par2")
    private String xe;
    private int id;

    public Food(String name, String url, String xe) {
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

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", xe='" + xe + '\'' +
                ", id=" + id +
                '}';
    }
}
