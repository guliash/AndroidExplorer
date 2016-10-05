package com.github.guliash.androidexplorer;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocalBroadcastManager.getInstance(this).registerReceiver(new Receiver1(), new IntentFilter("BABAH"));
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("BABAH"));

    }
}
