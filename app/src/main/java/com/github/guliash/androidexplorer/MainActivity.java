package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;

public class MainActivity extends BaseActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        getSupportFragmentManager().beginTransaction().add(R.id.container, new MyFragment()).commit();
    }
}
