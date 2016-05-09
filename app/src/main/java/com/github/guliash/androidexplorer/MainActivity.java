package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.post)
    Button mPost;

    @BindView(R.id.result)
    TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.post)
    void onPostClick() {
        EventBus.getDefault().postSticky(new MyObject("wow"));
    }

    @Subscribe
    void onResult(MyObject object) {
        Log.e(TAG, "POST " + object);
    }

    static class MyObject {
        String val;

        public MyObject(String val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val;
        }
    }
}
