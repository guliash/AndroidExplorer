package com.github.guliash.androidexplorer;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TestBehavior extends CoordinatorLayout.Behavior<TextView> {

    private static final String TAG = TestBehavior.class.getName();

    public TestBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, TextView child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, TextView child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.d(TAG, "Pre " + dx + " " + dy);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, TextView child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        Log.d(TAG, "On stop nested scroll");
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, TextView child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d(TAG, "On nested scroll " + dxConsumed + " " + dyConsumed + " " + dxUnconsumed + " " + dyUnconsumed);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, TextView child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.d(TAG, "On nested fling " + velocityX + " " + velocityY);
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, TextView child, View target, float velocityX, float velocityY) {
        Log.d(TAG, "On nested pre fling " + velocityX + " " + velocityY);
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityX);
    }
}