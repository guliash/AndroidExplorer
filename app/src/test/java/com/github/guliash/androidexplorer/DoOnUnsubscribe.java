package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

public class DoOnUnsubscribe {

    @Test
    public void doOnUnsubscribeWithUnsubscribe() {

        PrintSubscriber subscriber = new PrintSubscriber();

        Subscription subscription = Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(() -> System.out.println("1s"))
                .doOnUnsubscribe(() -> System.out.println("3s"))
                .subscribe(subscriber);

        subscription.unsubscribe();

    }

    @Test
    public void doOnUnsubscribeWithTermination() {

        PrintSubscriber subscriber = new PrintSubscriber();

        Subscription subscription = Observable.empty()
                .doOnUnsubscribe(() -> System.out.println("unsubscribe"))
                .subscribe(subscriber);

        subscription.unsubscribe();

    }

}
