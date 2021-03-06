package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Food_list extends AppCompatActivity {    // класс активности съеденных продуктов

    private Food_list_db food_list_db;
    private EatingFood_Adapter adapter;
    private ListView listView;
    private int lastPosition,count;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {    // создание активности и необходиых объектов

        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activiti);
        listView = (ListView)findViewById(R.id.food_list_show);
        food_list_db = new Food_list_db(getApplicationContext());
        adapter = new EatingFood_Adapter(getApplicationContext(),food_list_db.selectAll());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteItem(position);
            }
        });
    }

    public void deleteItem(int position) { // удаление записи

        if(lastPosition == position){
            count++;
        }
        if(count == 3){

            String Time = adapter.getItem(position).getTime(); // получаем время добавления

            lastPosition = position;

            food_list_db.delete(food_list_db.searchID(Time)); // ищем ид и удаляем запись

            adapter.clear();  // "Перезагружаем" адаптер

            adapter.addAll(food_list_db.selectAll());

            count = 0;

            Toast.makeText(this,"Запись удалена",Toast.LENGTH_LONG).show();
        }
        lastPosition = position;
    }

    public  void Clickback (View view){  // на главный экран

        Intent intent = new Intent(Food_list.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    public  void ClickAdd(View view){                // к экрану добавления
        Intent intent = new Intent(Food_list.this, Food_jornal.class);
        startActivity(intent);
        finish();

    }
}
