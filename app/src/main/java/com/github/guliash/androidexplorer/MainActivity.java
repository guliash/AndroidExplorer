package com.github.guliash.androidexplorer;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.parent)
    LinearLayout mLinear;

    @BindView(R.id.image)
    ImageView mImage;

    @BindView(R.id.go)
    Button mGo;

    @BindView(R.id.seekbar)
    SeekBar mSeekbar;

    @BindView(R.id.group)
    RadioGroup mRadioGroup;

    private static final float DEFAULT_ANIM_DURATION = 1; //seconds
    private float mAnimDuration = DEFAULT_ANIM_DURATION * 1000; //ms

    private ObjectAnimator mObjectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSeekbar.setProgress((int)(DEFAULT_ANIM_DURATION * 10));

        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAnimDuration = (progress / 10f) * 1000;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    @OnClick(R.id.go)
    void goButtonClicked() {
        if (mObjectAnimator != null) {
            mObjectAnimator.cancel();
        }
        int id = mRadioGroup.getCheckedRadioButtonId();
        TimeInterpolator interpolator = null;
        switch (id) {
            case R.id.acc_dec:
                interpolator = new AccelerateDecelerateInterpolator();
                break;
            case R.id.acc:
                interpolator = new AccelerateInterpolator();
                break;
            case R.id.ant:
                interpolator = new AnticipateInterpolator();
                break;
            case R.id.ant_over:
                interpolator = new AnticipateOvershootInterpolator();
                break;
            case R.id.bounce:
                interpolator = new BounceInterpolator();
                break;
            case R.id.cycle:
                interpolator = new CycleInterpolator(5);
                break;
            case R.id.dec:
                interpolator = new DecelerateInterpolator(5);
                break;
            case R.id.linear:
                interpolator = new LinearInterpolator();
                break;
            case R.id.over:
                interpolator = new OvershootInterpolator();
                break;
        }
        float start = mImage.getTop();
        float end = mLinear.getHeight() - mLinear.getPaddingBottom() - mImage.getHeight();
        mObjectAnimator = ObjectAnimator.ofFloat(mImage, "y", start, end);
        mObjectAnimator.setDuration((long)mAnimDuration);
        mObjectAnimator.setInterpolator(interpolator);

        mObjectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
            }
        });
        mObjectAnimator.start();
    }


}
