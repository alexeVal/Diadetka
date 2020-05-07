package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Food_list extends AppCompatActivity {

    Food_list_db food_list_db;
    EatingFood_Adapter adapter;
    ListView listView;
    NotificationHelp notification;
    int lastPosition,count;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activiti);
        listView = (ListView)findViewById(R.id.food_list_show);
        food_list_db = new Food_list_db(getApplicationContext());
        adapter = new EatingFood_Adapter(getApplicationContext(),food_list_db.selectAll());
        listView.setAdapter(adapter);
    }

    public void deleteItem(int position) {

        if(lastPosition == position){
            count++;
        }
        if(count == 3){

            String level = adapter.getItem(position).getName();

            lastPosition = position;

            food_list_db.delete(food_list_db.searchID(level));

            adapter.clear();

            adapter.addAll(food_list_db.selectAll());

            count = 0;

            notification.ShowNotification("Напоминание удалено","",NotificationHelp.PENSIL);
        }
        lastPosition = position;
    }

    public  void Clickback (View view){

        Intent intent = new Intent(Food_list.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public  void ClickAdd(View view){

        Intent intent = new Intent(Food_list.this, Food_jornal.class);
        startActivity(intent);
        finish();

    }
}
