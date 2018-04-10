package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Emitter;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class EmitterTest {

    @Test
    public void emitterTest() {

        PrintSubscriber printSubscriber = new PrintSubscriber();
        Observable observable = Observable.create(emitter -> {

            Observable.timer(2000, TimeUnit.MILLISECONDS)
                    .subscribe(v -> {}, e -> {}, emitter::onCompleted);

            emitter.setCancellation(() -> System.out.println("CANCELLED"));
        }, Emitter.BackpressureMode.NONE)
                .doOnUnsubscribe(() -> System.out.println("Unsubscribe"));

        Subscription subscription = observable.subscribe(printSubscriber);

        printSubscriber.awaitTerminalEvent();

    }

}
