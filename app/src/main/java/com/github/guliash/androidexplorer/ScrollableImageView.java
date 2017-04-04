package com.github.guliash.androidexplorer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ScrollableImageView extends android.support.v7.widget.AppCompatImageView {

    private float prevMoveX;
    private float prevMoveY;

    public ScrollableImageView(Context context) {
        super(context);
    }

    public ScrollableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        if (action == MotionEvent.ACTION_MOVE) {
            final Drawable drawable = getDrawable();
            final int outOfBoundsWidth = drawable.getIntrinsicWidth() - getWidth();
            final int outOfBoundsHeight = drawable.getIntrinsicHeight() - getHeight();
            final int diffX = (int) (prevMoveX - event.getX());
            final int diffY = (int) (prevMoveY - event.getY());
            int scrollX = 0;
            int scrollY = 0;
            if (outOfBoundsWidth / 2 > Math.abs(getScrollX() + diffX)) {
                scrollX = diffX;
            }
            if (outOfBoundsHeight / 2 > Math.abs(getScrollY() + diffY)) {
                scrollY = diffY;
            }
            scrollBy(scrollX, scrollY);
        }

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            prevMoveX = event.getX();
            prevMoveY = event.getY();
            return true;
        }

        return super.onTouchEvent(event);
    }
}
