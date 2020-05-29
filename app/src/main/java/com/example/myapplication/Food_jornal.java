package com.example.myapplication;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Food_jornal extends AppCompatActivity {

    private ListView listView; // для отображения данных
    private Food_list_db food_list_db; // для работы с БД съеденых продуктов
    private SearchView searchView; // поиск..
    private Application_vk application_vk = new Application_vk(); // для работы с ВК
    private Food_db db; // для работы с БД продуктов
    private VK_ID_base vkIdBase; // БД ВК ИД
    private MyAdapter adapter; // адаптер для преобразования данных
    private ProgressBar progressBar;
    FullDbАssyncTask dbАssyncTask; // класс для ассинхронного заполнеия БД продуктов

    @Override
    protected void onCreate(Bundle savedInstanceState) { // создаем активность и необходимые обьекты

        super.onCreate(savedInstanceState);

        setContentView(R.layout.food_activiti);

        getSupportActionBar().hide();

        listView = findViewById(R.id.list);

        progressBar = (ProgressBar) findViewById(R.id.progres);

        progressBar.setVisibility(View.VISIBLE);

        searchView = (SearchView)findViewById(R.id.search);

        db = new Food_db(getApplicationContext());

        adapter = new MyAdapter(this, db.selectAll());

        dbАssyncTask = new FullDbАssyncTask(getApplicationContext(), new BdFullStatus() {
            @Override
            public void onFinish() {  // когда поток завершен

                // перезапускаем активность
                Intent intent = new Intent(Food_jornal.this,Food_jornal.class);
                finish();
                startActivity(intent);
            }
        });

        if(db.checkBase()){   // если база продуктов пуста
               dbАssyncTask.execute(); // заполняем ее

        }else{
            progressBar.setVisibility(View.GONE);
            adapter.addAll(db.selectAll());
            listView.setAdapter(adapter);
        }

        food_list_db = new Food_list_db(getApplicationContext());

        vkIdBase = new VK_ID_base(getApplicationContext());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { // если выбрали продукт из списка ...
                String name = adapter.getName(position);
                String xe = adapter.getXE(position);
                food_list_db.insert(name, xe, getStringDate());
                application_vk.sendMSG(getStringDate() + "\n" + "Новая запись в дневник питания: " + name + "\n" + "Одна хлебная единица : " + xe, vkIdBase.selectAll());
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // поиск в списке (пока не реализован :( )
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    String getStringDate() {                                         // получить текущую дату и время
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String time = timeFormat.format(date);
        String da = dateFormat.format(date);
        return da + " " + time;
    }

    public void BackClick(View view) {       // на главный экран
        Intent intent = new Intent(Food_jornal.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}






