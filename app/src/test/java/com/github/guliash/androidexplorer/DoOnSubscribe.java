package com.github.guliash.androidexplorer;

import org.junit.*;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

public class DoOnSubscribe {

    @org.junit.Test
    public void doOnSubscribe() {

        PrintSubscriber subscriber = new PrintSubscriber();

        Subscription subscription = Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(() -> System.out.println("1s"))
                .doOnUnsubscribe(() -> System.out.println("3s"))
                .subscribe(subscriber);

        subscription.unsubscribe();

    }

}
