package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void  toMed(View view){
        Intent intent = new Intent(MainActivity.this,Calculator.class);
        startActivity(intent);
        finish();
    }

    public  void  tofood(View view){
        Intent intent = new Intent(MainActivity.this,Food_list.class);
        startActivity(intent);
        finish();
    }

    public  void  toJornal(View view){
        Intent intent = new Intent(MainActivity.this,Jornal.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.seting:
                Intent intent = new Intent(MainActivity.this,Setings.class);
                startActivity(intent);
                return  true;

            case R.id.about:

                return  true;

            case R.id.exit:
                finish();
                return  true;

        }
      return super.onOptionsItemSelected(item);
    }
}

