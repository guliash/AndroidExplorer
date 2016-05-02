package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends BaseActivity {

    @BindView(R.id.red)
    View mRed;

    @BindView(R.id.blue)
    View mBlue;

    @BindView(R.id.green)
    View mGreen;

    @BindView(R.id.orange)
    View mOrange;

    @BindView(R.id.root)
    ViewGroup mRoot;

    @BindView(R.id.group)
    RadioGroup mRadioGroup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.root)
    void rootClick() {
        int selected = mRadioGroup.getCheckedRadioButtonId();
        switch (selected) {
            case R.id.fade:
                TransitionManager.beginDelayedTransition(mRoot, new Fade());
                break;
            case R.id.explode:
                TransitionManager.beginDelayedTransition(mRoot, new Explode());
                break;
            case R.id.slide:
                TransitionManager.beginDelayedTransition(mRoot, new Slide());
                break;
        }
        toggle(mRed, mBlue, mGreen, mOrange);
    }

    private void toggle(View...views) {
        for(View view : views) {
            view.setVisibility(view.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        }
    }
}
