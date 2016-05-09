package com.github.guliash.androidexplorer;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.result)
    TextView mResult;

    @BindView(R.id.start)
    TextView mStart;

    @BindView(R.id.stop)
    TextView mStop;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e(TAG, "ON RECEIVE");

            mResult.setText(R.string.done);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver,
                new IntentFilter(Constants.RETURN_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "ON ACTIVITY RESULT");
    }

    @OnClick(R.id.start)
    void onStartClick() {
        Log.e(TAG, "START CLICK");
        mResult.setText("");
        Intent intent = new Intent(this, MyService.class);
        PendingIntent pi = createPendingResult(1, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
        intent.putExtra("pi", pi);
        startService(intent);
    }

    @OnClick(R.id.stop)
    void onStopClick() {
        stopService(new Intent(this, MyService.class));
    }

}
