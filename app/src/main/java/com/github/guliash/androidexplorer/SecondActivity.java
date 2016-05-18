package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends BaseActivity implements TestAsyncTask.Callbacks{

    private static final String DIALOG_TAG = "TAG";

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
        ProgressDialogFragment progressDialogFragment = ProgressDialogFragment.newInstance("WORKING HARD BABY", false);
        progressDialogFragment.show(getSupportFragmentManager(), DIALOG_TAG);
    }

    @Override
    public void onResult(String result) {
        Log.e(TAG, "RESULT " + result);
        DialogFragment dialog = (DialogFragment)getSupportFragmentManager().findFragmentByTag(DIALOG_TAG);
        if(dialog != null) {
            dialog.dismiss();
        }
        setResult(RESULT_OK);
        finish();
    }
}
