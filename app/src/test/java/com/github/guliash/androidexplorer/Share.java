package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;

public class Share {
    @Test
    public void publish_need_connect() throws InterruptedException {
        Observable<Long> publish = Observable.interval(1000, TimeUnit.MILLISECONDS).share();

        Subscription subscription = publish.subscribe(new PrintSubscriber());

        Thread.sleep(2000);

        Subscription subscription1 = publish.subscribe(new PrintSubscriber());

        subscription.unsubscribe();

        Thread.sleep(5000);

        subscription1.unsubscribe();

        PrintSubscriber subscriber = new PrintSubscriber();

        publish.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }
}
