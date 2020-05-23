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

public class MyReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        List_db list_db = new List_db(context);
        NotificationHelp notification = new NotificationHelp(context);
        notification.ShowNotification(list_db.getTextForTime(getStringDate()),"Напоминание",NotificationHelp.CLOCK);

    }
    String getStringDate(){
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String time = timeFormat.format(date);
        String da = dateFormat.format(date);
        return da + " " + time ;
    }


}