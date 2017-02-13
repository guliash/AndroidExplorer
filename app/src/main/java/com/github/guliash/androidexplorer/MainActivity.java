package com.github.guliash.androidexplorer;

import android.content.Intent;
import android.os.Bundle;

import com.guliash.mylibrary.TestActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MAIN" + findViewById(R.id.root));


        findViewById(R.id.click).setOnClickListener((view) -> {
            System.out.println("click");
            startActivity(new Intent(this, TestActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
