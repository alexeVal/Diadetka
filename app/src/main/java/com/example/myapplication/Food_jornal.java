package com.example.myapplication;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Food_jornal extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private Food_list_db food_list_db;
    private SearchView searchView;
    private Application_vk application_vk = new Application_vk();
    private Food_db db;
    private VK_ID_base vkIdBase;
    private MyAdapter adapter;
    private Application_server server;
    private FileLoading loader;

    //food = gsonС.fromJson(read,Food.class);
    //db.insert(food.getName(),food.getUrl(),food.getXe());
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_activiti);

        getSupportActionBar().hide();

        listView = findViewById(R.id.list);

        server = new Application_server(getApplicationContext());

        food_list_db = new Food_list_db(getApplicationContext());

        db = new Food_db(getApplicationContext());

        fulling_base();

        adapter = new MyAdapter(this, db.selectAll());

        listView.setAdapter(adapter);

        vkIdBase = new VK_ID_base(getApplicationContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = adapter.getName(position);
                String xe = adapter.getXE(position);
                food_list_db.insert(name, xe, getStringDate());
                application_vk.sendMSG(getStringDate() + "\n" + "Новая запись в дневник питания: " + name + "\n" + "Одна хлебная единица : " + xe, vkIdBase.selectAll());
            }
        });
    }

    String getStringDate() {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String time = timeFormat.format(date);
        String da = dateFormat.format(date);
        return da + " " + time;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Food_jornal.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void fulling_base() {
        File file = new File(getExternalCacheDir(), "base.txt");
        String read = "";
        Gson gsonС = new Gson();
        Food food;
        if (db.selectAll().size() == 0) { //если база продуктов пуста
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Food_jornal.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            server.DownloadBase();
            try {
                FileInputStream in = new FileInputStream(file);
                Scanner scanner = new Scanner(in);
                while (scanner.hasNext()){
                    read = scanner.nextLine();
                    Log.d("Прочитанный файл",read);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}






