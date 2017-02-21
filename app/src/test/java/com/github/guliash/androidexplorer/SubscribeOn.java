package com.github.guliash.androidexplorer;

import org.junit.*;

import rx.Observable;
import rx.schedulers.Schedulers;

public class SubscribeOn {

    @org.junit.Test
    public void test() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Observable.just(1, 2, 3)
                .map(v -> v * 10)
                .doOnSubscribe(() -> {
                    System.out.println("Thread " + Thread.currentThread().getName());
                })
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }
}
