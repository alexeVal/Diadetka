package com.example.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.view.Display;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NotificationHelp extends Activity {

    Context context;

    public NotificationHelp(Context context) {
        this.context = context;
    }

     static final int PENSIL = R.drawable.pensil;
     static final int CLOCK = R.drawable.clock;
    private final static String CHANNEL_ID = "DIADETKA_CHANEL";
    private int NOTIFY_ID = 1;

    NotificationManager notificationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void ShowNotification(String title, String txt, int ImageID) {

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setAutoCancel(false)
                        .setContentTitle(title)
                        .setContentText(txt)
                        .setSmallIcon(ImageID);
        notificationManager.notify(NOTIFY_ID,notificationBuilder.build());

        NOTIFY_ID++;
    }

    public static void createChannel(@NonNull NotificationManager notificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID," Remember_NOTE", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }
}


