package com.github.guliash.androidexplorer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import timber.log.Timber;

public class MyCustomLayout extends LinearLayout {

    public MyCustomLayout(Context context) {
        this(context, null);
    }

    public MyCustomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.inflated, this);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        Timber.d("add view");
        super.addView(child, index, params);
    }
}
