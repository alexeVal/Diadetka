package com.example.myapplication;

//by AlexeyVal 2020

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class Add_list extends AppCompatActivity {

    //для выбора времени и даты тревоги
    private TimePicker timePicker;
    private DatePicker datePicker;
    private EditText textE;  // для получения текста заметки
    private List_db list_db;  // для работы с БД
    // флаги дополнительных опций
    private CheckBox checkBox;
    private CheckBox checkBox2;

    AlarmManager alarmManager;
    int requestCode = 1;
    Button button;
    String month,day,hour,minute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {    //создаем активность и необходимые для работы объекты
            super.onCreate(savedInstanceState);
            setContentView(R.layout.list_add);
            textE = findViewById(R.id.list_txt);
            button = findViewById(R.id.add_butt);
            datePicker = findViewById(R.id.dateP);
            timePicker = findViewById(R.id.timeP);
            list_db = new List_db(this);
            checkBox = findViewById(R.id.chekShow);
            checkBox2 = findViewById(R.id.chekMark);
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        }

        public void onClick(View view){   // метод добавления заметки

        int isRed = 0;

        if(checkBox2.isChecked()){
            isRed = 1;
        }

        list_db.insert(getStringSetupDate(),textE.getText().toString(),isRed);

            if(checkBox.isChecked()) {
                setAlarm();
            }

            Intent intent = new Intent(Add_list.this, Jornal.class);
            startActivity(intent);
            finish();
        }


        void setAlarm (){                                       // установка тревоги
            requestCode = 1 + (int) (Math.random() * 9999);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.MONTH,datePicker.getMonth());
            calendar.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
            calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
            calendar.set(Calendar.MINUTE,timePicker.getMinute());
            calendar.set(Calendar.SECOND,0);

            Intent intent = new Intent(this,MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,requestCode,intent,PendingIntent.FLAG_ONE_SHOT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }

        String getStringSetupDate(){                    // получение выбранных даты и времени в формате 29.05 16:13

            month = Integer.toString(datePicker.getMonth() + 1);

            if(datePicker.getMonth() < 10){
                month = "0" + month;
            }

            day = Integer.toString(datePicker.getDayOfMonth());
            hour = Integer.toString(timePicker.getHour());
            minute = Integer.toString(timePicker.getMinute());

            return day + "." + month + " " + hour + ":" + minute ;
        }
    }

