package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Food_jornal extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    Food_list_db food_list_db;
    SearchView searchView;
    Application_vk application_vk = new Application_vk();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_activiti);

        getSupportActionBar().hide();

        food_list_db = new Food_list_db(getApplicationContext());

        Food_db db = new Food_db(getApplicationContext());

        final MyAdapter adapter = new MyAdapter(this,db.selectAll());

         listView = findViewById(R.id.list);

         searchView = findViewById(R.id.search);

         db.deleteAll();

         db.insert("Яблоко","https://avatars.mds.yandex.net/get-pdb/918543/7e32c556-5b07-41c8-b3f8-1f25d1810450/s1200?webp=false","2 штуки");

         adapter.clear();

         adapter.addAll(db.selectAll());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = adapter.getName(position);
                String xe = adapter.getXE(position);
                food_list_db.insert(name,xe,getStringDate());
                application_vk.sendMSG(getStringDate()+"\n"+ "Новая запись в дневник питания: " + name + "\n" + "Одна хлебная единица : " + xe);
            }
        });
    }

    String getStringDate(){
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String time = timeFormat.format(date);
        String da = dateFormat.format(date);
        return da + " " + time ;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Food_jornal.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

