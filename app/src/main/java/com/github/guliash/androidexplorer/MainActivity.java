package com.github.guliash.androidexplorer;

import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMyComponent.create().plus().plus().inject(this);
    }
}
