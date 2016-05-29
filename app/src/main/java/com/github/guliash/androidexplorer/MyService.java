package com.github.guliash.androidexplorer;

import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends BaseService {

    private Handler mHandler;

    private IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        HandlerThread handlerThread = new HandlerThread("service", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
        mHandler = new Handler(handlerThread.getLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        final int counter = intent.getIntExtra("counter", -1);
        Log.e(TAG, "ON START COMMAND " + counter);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, counter + "");
                Log.e(TAG, Thread.currentThread().getName());
                stopSelf(startId);
            }
        });
        return START_NOT_STICKY;
    }

    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public void start(int counter) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("counter", counter);
        startService(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
