package com.github.guliash.androidexplorer;

import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.open)
    void onOpenClick() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("STRING", "BABAH");
        startActivity(intent);
    }
}
