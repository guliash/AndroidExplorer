package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.widget.Button;

import rx.Observable;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
