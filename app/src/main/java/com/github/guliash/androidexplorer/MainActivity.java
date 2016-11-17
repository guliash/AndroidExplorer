package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    List<Subscription> subscriptionList = new ArrayList<>();
    Observable<Void> clicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        clicks = RxView.clicks(button).share();

    }

    @OnClick(R.id.subscribe)
    void onSubscribeClick() {
        subscribe();
    }

    @OnClick(R.id.unsubscribe)
    void onUnsubscribeClick() {
        unsubscribe();
    }

    private void subscribe() {
        subscriptionList.add(clicks.subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Log.e("TAG1", "1");
                    }
                }));
        subscriptionList.add(clicks.subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Log.e("TAG2", "2");
                    }
                }));
    }

    private void unsubscribe() {
        for(Subscription subscription : subscriptionList) {
            subscription.unsubscribe();
        }
    }
}
