package com.github.guliash.androidexplorer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected static String TAG = "explorer";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "ON CREATE " + this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "ON DESTROY " + this);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "ON CREATE VIEW " + this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "ON DESTROY VIEW " + this);
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "ON START " + this);
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.e(TAG, "ON STOP " + this);
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.e(TAG, "ON RESUME " + this);
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e(TAG, "ON PAUSE " + this);
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TAG, "ON SAVE INSTANCE STATE " + this);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(Context context) {
        Log.e(TAG, "ON ATTACH " + this);
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        Log.e(TAG, "ON DETACH " + this);
        super.onDetach();
    }
}
