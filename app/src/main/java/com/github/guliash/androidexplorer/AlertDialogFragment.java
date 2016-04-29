package com.github.guliash.androidexplorer;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class AlertDialogFragment extends BaseDialogFragment {

    public static AlertDialogFragment newInstance() {
        AlertDialogFragment fragment = new AlertDialogFragment();
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        return new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setTitle(R.string.fragment_alert)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "FRAGMENT POSITIVE");
                    }
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "FRAGMENT NEGATIVE");
                    }
                }).setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e(TAG, "FRAGMENT NEUTRAL");
                    }
                }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Log.e(TAG, "FRAGMENT DISMISS DIALOG");
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.e(TAG, "FRAGMENT CANCEL DIALOG");
                    }
                }).create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        //THIS IS CALLED, NOT THE DIALOG'S ONE
        Log.e(TAG, "FRAGMENT CANCEL");
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        //THIS IS CALLED, NOT THE DIALOG'S ONE
        Log.e(TAG, "FRAGMENT DISMISS");
    }
}
