package com.github.guliash.androidexplorer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        asyncTask.execute(new Void[]{null});
        asyncTask2.execute(new Void[]{null});
    }

    AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void...params) {
            try {
                Log.e(TAG, "BACKGROUND 1");
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.e(TAG, "ON POST 1");
        }
    };

    AsyncTask<Void, Void, Void> asyncTask2 = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void...params) {
            try {
                Log.e(TAG, "BACKGROUND 2");
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Log.e(TAG, "ON POST 2");
        }
    };

}
