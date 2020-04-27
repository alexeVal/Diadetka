package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        List_db list_db = new List_db(context);
        NotificationHelp notification = new NotificationHelp(context);
        notification.showNote(list_db.getTextForTime(),"Напоминание",NotificationHelp.CLOCK);
    }


}