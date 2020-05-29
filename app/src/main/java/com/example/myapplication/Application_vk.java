package com.example.myapplication;

//https://api.vk.com/method/METHOD_NAME?PARAMETERS&access_token=ACCESS_TOKEN&v=5.103

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application_vk { // класс для работы с ВК

    private String VK_BASE_URL = "https://api.vk.com";
    private String ACESS_TOKEN = "1ddeaef2c3cb574ef9a21b6df9e6e89f6757a9590024607994485e3bdb4590bcad431ea074079611fb583";
    int random_id,usID;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(VK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RestApiVK restApi = retrofit.create(RestApiVK.class);

    public void sendMSG(String msg, ArrayList<Integer> user_id){      // метод отправки сообщений

            for (int i = 0;i < user_id.size(); i++) {
                random_id = 1 + (int) (Math.random() * 999999999);
                usID = user_id.get(i);
                Call <ModelAnswer>  call = restApi.messages_send(usID,random_id,msg,ACESS_TOKEN,5.103);
                call.enqueue(new Callback<ModelAnswer>() {
                    @Override
                    public void onResponse(Call<ModelAnswer> call, Response<ModelAnswer> response) {
                    }
                    @Override
                    public void onFailure(Call<ModelAnswer> call, Throwable t) {

                    }
                });
            }
    }
}

