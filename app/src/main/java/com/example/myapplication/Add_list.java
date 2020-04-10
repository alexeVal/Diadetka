package com.example.myapplication;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TimePicker;
import androidx.annotation.Nullable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_list extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add);

        final TimePicker timePicker = (TimePicker)findViewById(R.id.timeP);
       final DatePicker datePicker = (DatePicker)findViewById(R.id.dateP);

      final EditText  textE = (EditText)findViewById(R.id.list_txt);

        Button button = (Button)findViewById(R.id.add_butt);

        ScrollView scroll = (ScrollView)findViewById(R.id.scrollView1);

        final List_db list_db = new List_db(getApplicationContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("EEE, d MMM 20yy, HH:mm");
                Date date = new Date(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth(),timePicker.getHour(),timePicker.getMinute());
                String dat = df.format(date);
                list_db.insert(dat,textE.getText().toString());
                Intent intent = new Intent(Add_list.this, Jornal.class);
                startActivity(intent);

            }
        });

        }
        void setAlarm (){

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY,17);

        calendar.set(Calendar.MINUTE,57);

        calendar.set(Calendar.SECOND,0);
        }



    }

