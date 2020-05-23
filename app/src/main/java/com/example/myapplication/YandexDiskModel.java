package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class YandexDiskModel {
    @SerializedName("href")
     String href;
    @SerializedName("method")
    String metod;
    @SerializedName("templated")
    boolean templated;
}
