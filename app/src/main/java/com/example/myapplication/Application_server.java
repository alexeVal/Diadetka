package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application_server {
    //https://downloader.dst.yandex.ru/disk/53139aa0et584d3bac7eeab405d3574b/535320b4/YyjTJtEHob8R5WbpojJbiiUuU2HC_2JSTU0gW9qE0NHGW2uncmBjM_-IXun3Msyij96FTHQGSX-fDL-XwokDvA%3D%3D?uid=202727674&filename=base.txt&disposition=attachment&hash=&limit=0&content_type=application%2Fx-www-form-urlencoded&fsize=34524&hid=93528043563b8r55723a253f4730290a&media_type=document

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

   void DownloadBase(){
        Call<YandexDiskModel> call = restApi.getDownloadURL("base.txt");
            call.enqueue(new Callback<YandexDiskModel>() {
                @Override
                public void onResponse(Call<YandexDiskModel> call, Response<YandexDiskModel> response) {
                    if (response.code() == 200){
                      Url_download = response.body().href;
                      loader = new FileLoading(Url_download,"base.txt",context);
                       loader.StartLoading();
                    }
                    Log.d("Тест",response.message() + " " + Integer.toString(response.code()) + " "  + Url_download + " " + response.body().metod + " " + Boolean.toString(response.body().templated));
                }
                @Override
                public void onFailure(Call<YandexDiskModel> call, Throwable t) {
                    Log.d("Тест",t.getMessage());
                }
            });
        }
    }