package com.github.guliash.androidexplorer;

import org.junit.*;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.Schedulers;

public class FlatMap {

    @org.junit.Test
    public void testScheduler() {
        PrintSubscriber<Long> subscriber = new PrintSubscriber<>();
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(Schedulers.io())
                .doOnNext(ø -> {
                    System.out.println(Thread.currentThread().getName());
                })
                .flatMap(v -> Observable.timer(1, TimeUnit.SECONDS).subscribeOn(Schedulers.computation()))
                .doOnNext(ø -> {
                    System.out.println(Thread.currentThread().getName());
                })
                .take(10)
        .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

}
