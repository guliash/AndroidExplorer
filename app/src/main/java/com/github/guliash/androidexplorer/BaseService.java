package com.github.guliash.androidexplorer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class BaseService extends Service {

    protected final static String TAG = "explorer";

    @Override
    public void onCreate() {
        Log.e(TAG, "ON CREATE " + this);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "ON DESTROY " + this);
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "ON START COMMAND " + this);
        return super.onStartCommand(intent, flags, startId);
    }
}
