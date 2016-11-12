package com.github.guliash.androidexplorer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.textview)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        String string = "Show mustgo on";
        SpannableStringBuilder spannableString = new SpannableStringBuilder(string);
        StyleSpan span = new StyleSpan(Typeface.BOLD);
        spannableString.setSpan(span,
                string.indexOf("must"), string.indexOf("must") + 4,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.ITALIC),
                string.indexOf("must"), string.indexOf("must") + 4,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        URLSpan urlSpan = new URLSpan("example.com") {
            @Override
            public void onClick(View widget) {
                Log.e("TAG", "BABAH " + getURL());
            }
        };
        spannableString.setSpan(urlSpan, string.indexOf("must"), string.indexOf("must") + 4,
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        spannableString.insert(string.indexOf("must"), "BABAH");
        spannableString.insert(string.indexOf("must"), "WOW");
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
