package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements TestAsyncTask.Callbacks {

    private static final String DIALOG_TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onResult(String result) {
        DialogFragment dialog = (DialogFragment)getSupportFragmentManager().findFragmentByTag(DIALOG_TAG);
        if(dialog != null) {
            dialog.dismiss();
        }
    }

    @OnClick(R.id.btn)
    public void btnClick() {
        Log.e(null, "CLICK");
        new TestAsyncTask(this).execute();
        ProgressDialogFragment progressDialogFragment = ProgressDialogFragment.newInstance("WORKING HARD BABY", false);
        progressDialogFragment.show(getSupportFragmentManager(), DIALOG_TAG);
    }
}
