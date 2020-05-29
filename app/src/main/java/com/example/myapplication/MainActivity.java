package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                       // создать активность
        setContentView(R.layout.activity_main);
    }

    public  void  toMed(View view){
        Intent intent = new Intent(MainActivity.this, Shugar_list.class);
        startActivity(intent);                                                              // перейти к дневнику уровня сахара
        finish();
    }

    public  void  tofood(View view){
        Intent intent = new Intent(MainActivity.this,Food_list.class);
        startActivity(intent);                                                            // перейти к дневнику питания
        finish();
    }

    public  void  toJornal(View view){
        Intent intent = new Intent(MainActivity.this,Jornal.class);
        startActivity(intent);
        finish();                                                                      // перейти к заметкам
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {     // создание контекстного меню
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    // метод обработки контекстного меню
        int id = item.getItemId();
        switch (id){
            case R.id.seting:
                Intent intent = new Intent(MainActivity.this,Setings.class);
                startActivity(intent);
                finish();                       // перейти в настройки
                return  true;

            case R.id.about:
                                                // о приложении
                return  true;

            case R.id.exit:
                finish();                   // выйти из приложения
                return  true;

        }
      return super.onOptionsItemSelected(item);
    }
}

