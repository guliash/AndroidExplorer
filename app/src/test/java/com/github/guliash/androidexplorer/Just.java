package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Observable;
import rx.schedulers.Schedulers;

public class Just {

    @Test
    public void just() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Observable.fromCallable(() -> Observable.just(1, 2, 3, getInt())).subscribeOn(Schedulers.io()).subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    public int getInt() {
        System.out.println(Thread.currentThread().getName());
        return 42;
    }
}
