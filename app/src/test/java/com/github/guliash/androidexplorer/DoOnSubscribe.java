package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;

public class DoOnSubscribe {
    @Test
    public void doOnSubscribeWithUnsubscribe() {

        PrintSubscriber subscriber = new PrintSubscriber();

        Subscription subscription = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(() -> System.out.println("subscribe"))
                .subscribe(subscriber);

        subscription.unsubscribe();

    }

    @Test
    public void doOnSubscribeWithTermination() {

        PrintSubscriber subscriber = new PrintSubscriber();

        Subscription subscription = Observable.empty()
                .doOnSubscribe(() -> System.out.println("subscribe"))
                .subscribe(subscriber);

        subscription.unsubscribe();

    }
}
