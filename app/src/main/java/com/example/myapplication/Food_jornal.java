package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;


public class Food_jornal extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_activiti);

        ListView listView;


        ArrayList<Food> food = new ArrayList();
        MyAdapter adapter = new MyAdapter(this,food);
        Food_db db = new Food_db(this);

         listView = findViewById(R.id.list);
         SearchView searchView = findViewById(R.id.search);

        listView.setAdapter(adapter);
        Button button = findViewById(R.id.butt_retern);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Food_jornal.this, MainActivity.class);
        startActivity(intent);
    }



}

