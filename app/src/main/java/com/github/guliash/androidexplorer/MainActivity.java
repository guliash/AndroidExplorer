package com.github.guliash.androidexplorer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private MyService mService;
    private int mCounter = 0;

    @BindView(R.id.result)
    TextView mResult;

    @BindView(R.id.start)
    TextView mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, MyService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
    }

    @OnClick(R.id.start)
    void onStartClick() {
        Log.e(TAG, "START CLICK");
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("counter", mCounter++);
        startService(intent);
    }

    @OnClick(R.id.binder)
    void onBinderClick() {
        mService.start(mCounter++);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder)service;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
