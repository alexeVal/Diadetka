package com.example.myapplication;
//https://api.vk.com/method/METHOD_NAME?PARAMETERS&access_token=ACCESS_TOKEN&v=5.103

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application_vk  {

    String VK_BASE_URL = "https://api.vk.com";
    String ACESS_TOKEN = "1ddeaef2c3cb574ef9a21b6df9e6e89f6757a9590024607994485e3bdb4590bcad431ea074079611fb583";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(VK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private RestApi restApi = retrofit.create(RestApi.class);

    private int a = 1;
    private int b = 999999999;

     public void sendMSG(String msg){
        int random_id = a + (int) (Math.random() * b);
         Call <ModelAnswer>  call = restApi.messages_send(533603861,random_id,msg,ACESS_TOKEN,5.103);
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
