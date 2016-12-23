package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

public class SwitchMapTest {

    @Test
    public void simple() {
        ConnectableObservable<Long> observable = Observable.interval(100, TimeUnit.MILLISECONDS).publish();
        observable.connect();
        PrintSubscriber<Long> subscriber = new PrintSubscriber<>();
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .doOnNext(v -> System.out.println(v))
                .switchMap(val -> observable.take(1)
                        .doOnNext(i -> System.out.println(val)))
                .take(5)
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void ss() {
        ConnectableObservable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        cold.connect();
        PrintSubscriber<Long> subscriber = new PrintSubscriber<>();
        cold.subscribe(subscriber);
        subscriber.awaitTerminalEvent();
    }

}
