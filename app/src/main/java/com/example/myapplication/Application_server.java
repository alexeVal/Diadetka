package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application_server {    // класс для работы с Яндекс диском

    // поля необходимые для запроса
    private String BaseURL = "https://cloud-api.yandex.net";
    private String Url_download;
    private Context context;
    private FileLoading loader;

    public Application_server(Context context) {
        this.context = context;
    }

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ServerAPP restApi = retrofit.create(ServerAPP.class);

   void DownloadBase(){                                                         // получаем ссылку на скачивание файла базы данных
        Call<YandexDiskModel> call = restApi.getDownloadURL("base.txt");
            call.enqueue(new Callback<YandexDiskModel>() {
                @Override
                public void onResponse(Call<YandexDiskModel> call, Response<YandexDiskModel> response) {
                    if (response.code() == 200){
                      Url_download = response.body().href;
                      loader = new FileLoading(Url_download,"base.txt",context);
                      loader.StartLoading();  //если все ок начинаем скачивание
                    }
                }
                @Override
                public void onFailure(Call<YandexDiskModel> call, Throwable t) {
                   t.printStackTrace();
                }
            });
        }
    }