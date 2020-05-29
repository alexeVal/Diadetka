package com.example.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FullDbАssyncTask extends AsyncTask<Void,Void,Void> { // класс для ассинхронного заполнения БД

    private Gson gson = new Gson();
    private Food food;
    private  Application_server server;
    private File file;
    private Food_db db;
    private BdFullStatus fullStatus;

    public FullDbАssyncTask(Context context,BdFullStatus status) {
       server= new Application_server(context);
       file= new File(context.getExternalCacheDir(), "base.txt");
       db = new Food_db(context);
       fullStatus = status;
    }

    public ArrayList<String> readBase(File file) { // метод чтения скачаного файла
        String read = " ";
        ArrayList<String> fileRading = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(file);
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                read = scanner.nextLine();
                fileRading.add(read);
            }
        } catch (FileNotFoundException e) {
        }
        return fileRading; // возвращаем прочитанный файл
    }

    @Override
    protected Void doInBackground(Void...voids) {

        server.DownloadBase();      // скачиваем файл
        while (true){
            if(file.exists()){   // ждем окончания загрузки
                break;
            }
        }
            for(int i = 1; i < readBase(file).size();i++){
                String reads = readBase(file).get(i); // построчно читаем файл
                food = gson.fromJson(reads,Food.class); // преобразуем полученую gson строку в обьект
                db.insert(food.getName(),food.getUrl(),food.getXe()); // заполняем БД
            }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        fullStatus.onFinish();
    }  // когда поток завершен

}

