package com.github.guliash.androidexplorer;

import android.app.PendingIntent;
import android.content.Intent;

public class MyService extends BaseService {

    private long SLEEP_TIME = 5000;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final PendingIntent pi = intent.getExtras().getParcelable("pi");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SLEEP_TIME);
                    pi.send();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (PendingIntent.CanceledException e) {
                    e.printStackTrace();
                } finally {
                    stopSelf();
                }
            }
        }).start();
        return START_NOT_STICKY;
    }
}
