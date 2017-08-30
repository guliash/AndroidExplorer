package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Observable;


public class Just {

    @Test
    public void just() {
        PrintSubscriber subscriber = new PrintSubscriber();

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void testOperatorError() {
        Observable.just(1)
                .map(it -> {
                    throw new IllegalStateException("1");
                })
                .subscribe(new PrintSubscriber<>());
    }

    public int getInt() {
        System.out.println(Thread.currentThread().getName());
        return 42;
    }
}
