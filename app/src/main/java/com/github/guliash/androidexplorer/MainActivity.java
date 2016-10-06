package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.go_button)
    Button mGoButton;

    private boolean mFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.go_button)
    void onClick() {
        mFlag = !mFlag;
        if(mFlag) {
            mGoButton.animate().cancel();
            mGoButton.animate().translationX(1000)
                    .setDuration(30000);
        } else {
            mGoButton.animate().cancel();
            mGoButton.animate().scaleX(0.5f).scaleY(0.5f)
                    .setDuration(10000);
        }
    }
}
