package com.github.guliash.androidexplorer;

import org.junit.*;

import java.util.concurrent.TimeUnit;

import rx.Completable;
import rx.Observable;

public class FlatMapCompletable {

    @org.junit.Test
    public void whatIsFlatMapCompletable() {
        PrintSubscriber subscriber = new PrintSubscriber();

        Observable.interval(1, TimeUnit.SECONDS)
                .take(5)
                .doOnNext(it -> System.out.println(it))
                .flatMapCompletable(it -> Completable.timer(it, TimeUnit.SECONDS))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

}
