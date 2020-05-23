package com.example.myapplication;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import java.io.File;

    public class FileLoading   {

        private String Url;
        private String filename;
        private Context context;
        private DownloadManager manager;

        public FileLoading(String url, String filename, Context context) {
            Url = url;
            this.filename = filename;
            this.context = context;
        }

       private File file;

        public void StartLoading(){
            Log.d("статус","Старт");
            file =new File(context.getExternalCacheDir(),filename);
            DownloadManager.Request request=new DownloadManager.Request(Uri.parse(Url))
                    .setTitle("Dummy File")// Title of the Download Notification
                    .setDescription("Downloading")// Description of the Download Notification
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)// Visibility of the download Notification
                    .setDestinationUri(Uri.fromFile(file));// Uri of the destination file
            Log.d("Скачивание",request.toString());
            if(manager != null){
                manager.enqueue(request);
            } else {
                manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
            }

        }
    }


