package com.github.guliash.androidexplorer;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class BaseIntentService extends IntentService {

    protected final static String TAG = "explorer";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public BaseIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "ON CREATE");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.e(TAG, "ON HANDLE INTENT " + intent.getExtras().getInt("counter"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "ON DESTROY");
    }
}
