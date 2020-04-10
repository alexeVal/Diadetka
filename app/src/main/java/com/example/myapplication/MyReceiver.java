package com.example.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    private AlarmManager alarmManager;

    public void setAlarmManager(AlarmManager alarmManager) {
        this.alarmManager = alarmManager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notification = new Notification(context);
        notification.showNote("Тревога!","",Notification.PENSIL);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,101,intent,PendingIntent.FLAG_NO_CREATE);
        alarmManager.cancel(pendingIntent);
    }


}