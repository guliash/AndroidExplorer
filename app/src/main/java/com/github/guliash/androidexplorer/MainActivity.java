package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.view.ViewStub;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.stub)
    ViewStub viewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewStub.inflate();

        ButterKnife.bind(this);

        System.out.println("debug " + viewStub);
    }
}
