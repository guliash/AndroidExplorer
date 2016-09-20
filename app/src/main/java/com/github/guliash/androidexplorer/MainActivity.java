package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout2, new BFragment())
                .commit();
        super.onStop();

    }
}
