package com.example.myapplication;

import java.net.URL;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ServerAPP {  // интерфейc запроса для работы c retrofit
    @Headers({"Authorization: OAuth AgAAAABA7QXbAAZZ3r24JRyNq0mWou2HulffPo4"})
    @GET("/v1/disk/resources/download")
    Call<YandexDiskModel> getDownloadURL (@Query("path") String path);
}
