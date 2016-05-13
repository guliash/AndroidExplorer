package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

        HandlerThread handlerThread = new HandlerThread("MyThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, Thread.currentThread().getName());
                try {
                    Thread.sleep(5000);
                    EventBus.getDefault().post(new MyObject("wow"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onResult(MyObject object) {
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
