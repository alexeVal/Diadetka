package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;


public class MainActivity extends AppCompatActivity {

    FileTread tread;
    File food,shugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);                       // создать активность
        setContentView(R.layout.activity_main);
         food= new File(getExternalCacheDir(),"дневник питания.txt");
         shugar = new File(getExternalCacheDir(),"отчет об уровне сахара.txt");
        Shugar_db db = new Shugar_db(getApplicationContext());
        Food_list_db fdb = new Food_list_db(getApplicationContext());
        tread = new FileTread(food,shugar,db,fdb);
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

            case R.id.about:                   // получить отчеты
                tread.start();
                Toast.makeText(getApplicationContext(),"Файлы сохранены в " + food.getAbsolutePath(),Toast.LENGTH_LONG ).show();
                return  true;

            case R.id.exit:
                finish();                   // выйти из приложения
                return  true;
        }
      return super.onOptionsItemSelected(item);
    }
}
 class FileTread extends Thread {

     private File food, shugar;
     private Shugar_db db;
     private Food_list_db fdb;
     private String writing;

         FileTread(File food, File shugar, Shugar_db db, Food_list_db fdb) {
         this.food = food;
         this.shugar = shugar;
         this.db = db;
         this.fdb = fdb;
     }

     @Override
     public void run() {

             try {                                  // составляем отчет об уровне сахара

                 shugar.createNewFile();

                 FileOutputStream streamS = new FileOutputStream(shugar);

                 for (int i = 0; i < db.selectAll().size(); i++) {
                     writing = "Время: " + db.selectAll().get(i).getTime() + " Уровень сахара: " + db.selectAll().get(i).getLevel() + "\n";
                     streamS.write(writing.getBytes("UTF-8"));
                 }
                 streamS.close();

            } catch (IOException e) {
                 e.printStackTrace();
            }


             try {                                            // составляем дневник питания

                 food.createNewFile();

                 FileOutputStream streamF = new FileOutputStream(food);

                 for (int i = 0; i < fdb.selectAll().size(); i++) {
                     writing = "Время: " + fdb.selectAll().get(i).getTime() + " Название: " + fdb.selectAll().get(i).getName() + " Хлебная единица: " + fdb.selectAll().get(i).getXe() + "\n";
                     streamF.write(writing.getBytes("UTF-8"));
                 }
                 streamF.close();

             } catch (IOException e) {
                 e.printStackTrace();
             }

         Log.d("Тестер","готово");
     }
 }



