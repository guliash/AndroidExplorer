package com.github.guliash.androidexplorer;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class TestAsyncTask extends AsyncTask<Void, Void, String> {

    private WeakReference<Callbacks> mCallbacks;

    public TestAsyncTask(Callbacks callbacks) {
        mCallbacks = new WeakReference<Callbacks>(callbacks);
    }

    public interface Callbacks {
        void onResult(String result);
    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "WOW";
    }

    @Override
    protected void onPostExecute(String result) {
        Callbacks callbacks = mCallbacks.get();
        if(callbacks != null) {
            callbacks.onResult(result);
        }
    }
}
