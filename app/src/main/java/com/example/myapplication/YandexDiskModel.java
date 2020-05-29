package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

// класс модель для retrofit

public class YandexDiskModel {
    @SerializedName("href")
     String href;
    @SerializedName("method")
    String metod;
    @SerializedName("templated")
    boolean templated;
}
