package com.github.guliash.androidexplorer;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

@CoordinatorLayout.DefaultBehavior(MyFab.MyBehavior.class)
public class MyFab extends FloatingActionButton {
    public MyFab(Context context) {
        super(context);
    }

    public MyFab(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static class MyBehavior extends CoordinatorLayout.Behavior<MyFab> {
        @Override
        public boolean layoutDependsOn(CoordinatorLayout parent, MyFab child, View dependency) {
            return dependency instanceof Snackbar.SnackbarLayout;
        }

        @Override
        public boolean onDependentViewChanged(CoordinatorLayout parent, MyFab child, View dependency) {
            if(dependency instanceof Snackbar.SnackbarLayout) {
                updateFabByPosition(child, (Snackbar.SnackbarLayout.class.cast(dependency)));
                return true;
            }
            return false;
        }

        public void updateFabByPosition(MyFab fab, Snackbar.SnackbarLayout snack) {
            float snackYTranslation = ViewCompat.getTranslationY(snack);
            float height = snack.getHeight();
            ViewCompat.setTranslationY(fab, snackYTranslation - height);
        }
    }
}
