package com.github.guliash.androidexplorer;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.sendOrderedBroadcast(new Intent("BABAH"), null);
    }
}
