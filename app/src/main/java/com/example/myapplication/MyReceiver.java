package com.example.myapplication;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver { //класс приемник тревоги

    @Override
    public void onReceive(Context context, Intent intent) {  // когда пришло время  напоминания
        List_db list_db = new List_db(context);
        NotificationHelp notification = new NotificationHelp();
        notification.ShowNotification(list_db.getTextForTime(getStringDate()),"Напоминание",NotificationHelp.CLOCK,context);  // показываем уведомление
    }

    String getStringDate(){    // получаем текущую дату и время
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String tf = timeFormat.format(date);
        String df = dateFormat.format(date);
        return df + " " + tf ;
    }


}