package com.example.myapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

public class NotificationHelp { // класс для работы с уведомлениями

    static final int PENSIL = R.drawable.pensil; // ссылки на иконки уведомлений
    static final int CLOCK = R.drawable.clock;
    private final static String CHANNEL_ID = "DIADETKA_CHANEL";
    private int NOTIFY_ID = 1;
    NotificationManager notificationManager;

    public void ShowNotification(String title, String txt, int ImageID,Context context) {   // показываем уведомления

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createChannel(notificationManager);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setAutoCancel(false)
                        .setContentTitle(title)
                        .setContentText(txt)
                        .setSmallIcon(ImageID);
        notificationManager.notify(NOTIFY_ID,notificationBuilder.build());

        NOTIFY_ID++;
    }

    public static void createChannel(@NonNull NotificationManager notificationManager) { // создаем канал уведомления
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID," Remember_NOTE", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }
}


