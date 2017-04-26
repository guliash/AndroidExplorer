package com.github.guliash.androidexplorer;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private boolean visible = true;
    private MyFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragment = new MyFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.bottom, fragment, null).commit();
        }
    }

    @OnClick(R.id.button)
    void onClickButton() {
        if (visible) {
            getSupportFragmentManager().beginTransaction().hide(fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().show(fragment).commit();
        }
        visible = !visible;
    }
}
