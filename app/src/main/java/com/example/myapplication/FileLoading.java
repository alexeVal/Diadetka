package com.example.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import java.io.File;

    public class FileLoading   { // класс для загрузки с Диска

        private String Url;
        private String filename;
        private Context context;
        private DownloadManager manager;
        private File file;

        public FileLoading(String url, String filename, Context context) {
            Url = url;
            this.filename = filename;
            this.context = context;
        }

        public void StartLoading(){
            file =new File(context.getExternalCacheDir(),filename);
            DownloadManager.Request request=new DownloadManager.Request(Uri.parse(Url)) // готовим запрос к Download manager
                    .setTitle("Загрузка базы данных")
                    .setDescription("Downloading...")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                    .setDestinationUri(Uri.fromFile(file));
            if(manager != null){
                manager.enqueue(request);
            } else {
                manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request); // начинаем загрузку
            }
        }
    }


