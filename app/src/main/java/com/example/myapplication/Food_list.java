package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Food_list extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activiti);
        ListView listView = (ListView)findViewById(R.id.food_list_show);
        Food_list_db food_list_db = new Food_list_db(getApplicationContext());
        EatingFood_Adapter adapter = new EatingFood_Adapter(getApplicationContext(),food_list_db.selectAll());
        listView.setAdapter(adapter);
    }

    public  void Clickback (View view){

        Intent intent = new Intent(Food_list.this, MainActivity.class);
        startActivity(intent);

    }

    public  void ClickAdd(View view){

        Intent intent = new Intent(Food_list.this, Food_jornal.class);
        startActivity(intent);

    }
}
