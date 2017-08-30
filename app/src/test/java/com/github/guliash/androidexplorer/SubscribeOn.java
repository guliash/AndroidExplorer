package com.github.guliash.androidexplorer;

import org.junit.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

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


    @Test
    public void test1() {
        PrintSubscriber subscriber = new PrintSubscriber();

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .doOnNext(it -> System.out.println(" 1 " + Thread.currentThread().getName()))
                .observeOn(Schedulers.computation())
                .doOnNext(it -> System.out.println(" 2 " + Thread.currentThread().getName()))
                .observeOn(Schedulers.io())
                .doOnNext(it -> System.out.println(" 3 " + Thread.currentThread().getName()))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }
}
