package com.github.guliash.androidexplorer;

import android.content.Intent;
import android.util.Log;

public class MyService extends BaseIntentService {

    private long SLEEP_TIME = 10000;

    public MyService() {
        super("MY");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        super.onHandleIntent(intent);
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.e(TAG, "INTERRUPTED");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        int counter = intent.getExtras().getInt("counter");
        if(counter % 2 == 0) {
            return START_REDELIVER_INTENT;
        } else {
            return START_NOT_STICKY;
        }
    }
}
