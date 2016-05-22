package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
