package com.example.myapplication;

import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelp {
    Context context;

    public NotificationHelp(Context context) {
        this.context = context;
    }

  static final int PENSIL = R.drawable.pensil;
  static final int CLOCK = R.drawable.clock;

  int NOTIFY_ID;
    void showNote(String text,String title,int image){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context,"note_channel")
                        .setSmallIcon(image)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT).setDefaults(NotificationCompat.DEFAULT_ALL);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFY_ID, builder.build());

        NOTIFY_ID++;
    }
}
