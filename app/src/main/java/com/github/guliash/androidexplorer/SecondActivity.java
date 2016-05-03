package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        MainActivity.MyObject obj = bundle.getParcelable("KEY");
        Log.e(TAG, obj.intValue + " " + obj.doubleValue);
    }
}
