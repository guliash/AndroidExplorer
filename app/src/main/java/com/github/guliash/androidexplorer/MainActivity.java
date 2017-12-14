package com.github.guliash.androidexplorer;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.show)
    void onShowClick() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "id";

        NotificationChannel notificationChannel = new NotificationChannel(channelId, "test", NotificationManager.IMPORTANCE_HIGH);

        notificationChannel.setDescription("WOW");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.RED);
        notificationChannel.enableVibration(true);
        notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

        mNotificationManager.createNotificationChannel(notificationChannel);

        mNotificationManager.notify(1, new NotificationCompat.Builder(this, channelId).setSmallIcon(R.drawable.test).setContentText("Wtf1").build());
        mNotificationManager.notify(2, new NotificationCompat.Builder(this, channelId).setSmallIcon(R.drawable.test).setContentText("Wtf2").build());
    }
}
