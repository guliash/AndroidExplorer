package com.github.guliash.androidexplorer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

public class ProgressDialogFragment extends BaseDialogFragment {

    private static final String MESSAGE = "message";
    private static final String CANCELABLE = "cancelable";

    private String mMessage;
    private boolean mCancelable;

    /**
     *
     * @param message текст диалога
     * @param cancelable отменяемый ли диалог
     * @return инстанс диалога
     */
    public static ProgressDialogFragment newInstance(@Nullable String message, boolean cancelable) {
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, message);
        bundle.putBoolean(CANCELABLE, cancelable);
        ProgressDialogFragment fragment = new ProgressDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = savedInstanceState != null ? savedInstanceState : getArguments();

        mMessage = bundle.getString(MESSAGE);
        mCancelable = bundle.getBoolean(CANCELABLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(MESSAGE, mMessage);
        outState.putBoolean(CANCELABLE, mCancelable);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setCancelable(mCancelable);
        ProgressDialog dialog = new ProgressDialog(this.getActivity());
        if (!TextUtils.isEmpty(mMessage)) {
            dialog.setMessage(mMessage);
        }
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
        return dialog;
    }
}
