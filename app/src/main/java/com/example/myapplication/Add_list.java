package com.example.myapplication;

//by AlexeyVal 2020

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TimePicker;
import androidx.annotation.Nullable;
import java.util.Calendar;


public class Add_list extends Activity {

    String month,day,hour,minute;
    TimePicker timePicker;
    DatePicker datePicker;
    EditText textE;
    Button button;
    List_db list_db;
    ScrollView scrol;
    CheckBox checkBox;
    CheckBox checkBox2;
    AlarmManager alarmManager;
    int requestCode = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.list_add);

            textE = findViewById(R.id.list_txt);
            button = findViewById(R.id.add_butt);
            list_db= new List_db(getApplicationContext());
            datePicker = findViewById(R.id.dateP);
            timePicker = findViewById(R.id.timeP);
            scrol = findViewById(R.id.scrollView1);
            checkBox = findViewById(R.id.chekShow);
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        }

        public void onClick(View view){

            list_db.insert(getStringSetupDate(),textE.getText().toString());
            if(checkBox.isChecked()) {
                setAlarm();
            }
            Intent intent = new Intent(Add_list.this, Jornal.class);
            startActivity(intent);

        }

        void setAlarm (){

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
            requestCode++;
        }

        String getStringSetupDate(){

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

