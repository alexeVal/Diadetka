package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class Food_jornal extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    Food_list_db food_list_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_activiti);

        food_list_db = new Food_list_db(getApplicationContext());

        ArrayList<Food> food = new ArrayList();

        final MyAdapter adapter = new MyAdapter(this,food_list_db.selectAll());

        Food_db db = new Food_db(getApplicationContext());

         listView = findViewById(R.id.list);
         SearchView searchView = findViewById(R.id.search);

         db.insert("Печенье сахарное","https://cloud.mail.ru/public/3v1k/3VF6G7hrC" ,"2-4 штуки");
         db.insert("Яблоко красное","https://cloud.mail.ru/public/5m5y/3tR3TS2eu" ,"2-4 штуки");
         db.insert("Сухари","https://cloud.mail.ru/public/3QzS/4DLiPXeqZ" ,"50-60 грамм");

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = adapter.getName(position);
                String xe = adapter.getXE(position);
                food_list_db.insert(name,xe);

            }
        });

        Button button = findViewById(R.id.butt_retern);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Food_jornal.this, MainActivity.class);
        startActivity(intent);
    }



}

