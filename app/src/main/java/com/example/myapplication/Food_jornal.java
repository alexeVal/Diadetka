package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Food_jornal extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private Food_list_db food_list_db;
    private SearchView searchView;
    private Application_vk application_vk = new Application_vk();
    private Food_db db;
    private VK_ID_base vkIdBase;
    private MyAdapter adapter;
    private Application_server server;
    private boolean isReaded = false;
    private Food food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_activiti);

        getSupportActionBar().hide();

        listView = findViewById(R.id.list);

        Gson gson = new Gson();


        Log.d("Тест","конец");

        server = new Application_server(getApplicationContext());

        server.DownloadBase();

        gsonToFood(readBase());

        food_list_db = new Food_list_db(getApplicationContext());

        db = new Food_db(getApplicationContext());

        for(int i = 1; i < readBase().size();i++){
            String reads = readBase().get(i);
            food = gson.fromJson(reads,Food.class);
            db.insert(food.getName(),food.getUrl(),food.getXe());
            Log.d("Тест",food.getName());
        }

        adapter = new MyAdapter(this, db.selectAll());

        fulling_base();

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
        if (db.selectAll().size() == 0) { //если база продуктов пуста
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Food_jornal.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
    public ArrayList<String> readBase() {
        String read = " ";
        File file = new File(getExternalCacheDir(), "base.txt");
        ArrayList<String> fileRading = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(file);
            Scanner scanner = new Scanner(in);
            while (scanner.hasNext()) {
                read = scanner.nextLine();
                Log.d("Прочитанный файл", read);
                fileRading.add(read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileRading;
    }
    public void gsonToFood(ArrayList<String> gsons){

    }
}






