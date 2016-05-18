package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity implements TestAsyncTask.Callbacks{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
        Log.e(null, "CLICK");
        new TestAsyncTask(this).execute();
    }

    @Override
    public void onResult(String result) {
        Log.e(TAG, "RESULT " + this);
        finish();
    }
}
