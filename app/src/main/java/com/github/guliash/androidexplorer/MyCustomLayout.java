package com.github.guliash.androidexplorer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

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

}
