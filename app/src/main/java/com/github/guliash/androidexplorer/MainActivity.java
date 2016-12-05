package com.github.guliash.androidexplorer;

import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CComponent cComponent = DaggerCComponent.builder().bComponent(DaggerBComponent.builder().aComponent(DaggerAComponent.builder().aModule(new AModule("baah")).build()).build()).build();
        cComponent.inject(this);
    }
}
