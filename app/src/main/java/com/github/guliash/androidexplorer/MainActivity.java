package com.github.guliash.androidexplorer;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Provider;

public class MainActivity extends BaseActivity {

    @Inject
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerSubComponent.builder().myComponent(DaggerMyComponent.create()).build().inject(this);

//        DaggerMyComponent.create().plus().builder().build().inject(this);

//        DaggerMyComponent.create().plus().plus().inject(this);
    }
}
