package com.example.myapplication;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification {
    Context context;

    public Notification(Context context) {
        this.context = context;
    }

    // Идентификатор уведомления
    private static int NOTIFY_ID = 1;

    private static String CHANNEL_ID = "note_channel";

  static final int PENSIL = R.drawable.pensil;
  static final int CLOCK = R.drawable.clock;

    void showNote(String text,String title,int image){

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(image)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT).setDefaults(NotificationCompat.DEFAULT_ALL);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFY_ID, builder.build());

        NOTIFY_ID++;
    }
}
