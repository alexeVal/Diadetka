package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiVK {   //интерфейс запроса для retrofit
        //Анотация какой запрос и на какой путь на сервере отправляем
        @GET("/method/messages.send")
        //Метод с запросом и типом возвращаемого ответа
        Call<ModelAnswer> messages_send(@Query("user_id") int user_id, @Query("random_id") int random_id,@Query("message") String messege,@Query("access_token") String token,@Query("v") double v );
    }

