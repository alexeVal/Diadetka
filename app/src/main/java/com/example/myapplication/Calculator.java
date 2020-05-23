package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calculator extends AppCompatActivity {

    private Shugar_db shugar_db;
    private EditText editText;
    private Shugar_adapter shugar_adapter;
    private int lastPosition,count;
    private NotificationHelp notification;
    private Application_vk application_vk = new Application_vk();
    private double level;
    private VK_ID_base vkIdBase;

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activiti);
        getSupportActionBar().hide();
        shugar_db = new Shugar_db(this);
        editText = findViewById(R.id.Etxt1);
        shugar_adapter = new Shugar_adapter(this,shugar_db.selectAll());
        ListView shugar_list = (ListView)findViewById(R.id.shugarView);
        shugar_list.setAdapter(shugar_adapter);
        notification = new NotificationHelp(this);
        vkIdBase = new VK_ID_base(this);

        shugar_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteItem(position);
            }
        });
    }

    public void addShugar(View view){
        try {
            level = Double.parseDouble(editText.getText().toString());
            shugar_db.insert(level,getStringDate());
            application_vk.sendMSG(getStringDate() + "\n"+ "Новая запись в дневник уровня сахара : " + level,vkIdBase.selectAll());

        } catch (NumberFormatException e){
            Toast.makeText(this,"Неверный формат ввода! Используй только цифры и знак .",Toast.LENGTH_LONG).show();
        }
        shugar_adapter.clear();
        shugar_adapter.addAll(shugar_db.selectAll());
    }

    public void backClick(View view){
        Intent intent = new Intent(Calculator.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    String getStringDate(){
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String time = timeFormat.format(date);
        String da = dateFormat.format(date);
        return da + " " + time ;
    }

    public void deleteItem(int position) {

        if(lastPosition == position){
            count++;
        }
        if(count == 3){

            double level = shugar_adapter.getItem(position).getLevel();

            lastPosition = position;

            shugar_db.delete(shugar_db.searchID(level));

            shugar_adapter.clear();

            shugar_adapter.addAll(shugar_db.selectAll());

            count = 0;

            Toast.makeText(this,"Запись удалена",Toast.LENGTH_LONG).show();
        }
        lastPosition = position;
    }
}


