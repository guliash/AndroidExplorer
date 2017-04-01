package com.github.guliash.androidexplorer;

import org.junit.*;

import rx.Observable;
import rx.functions.Action2;

public class Merge {

    @org.junit.Test
    public void mergeDelayError() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Observable.mergeDelayError(Observable.error(new Throwable()), Observable.error(new Throwable()))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }
}
