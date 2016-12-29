package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Using {

    @Test
    public void using_threadCheck() {
        PrintSubscriber<Long> printSubscriber = new PrintSubscriber<>();
        Observable.using(() -> new Object(),
                object -> {
                    System.out.println("Body " + Thread.currentThread().getName());
                    return Observable.timer(2, TimeUnit.SECONDS);
                }, obj -> {
                    System.out.println("Dispose thread " + Thread.currentThread().getName());
                })
        .subscribe(printSubscriber);
        printSubscriber.awaitTerminalEvent();
    }

    @Test
    public void using_threadCheckScheduler() {
        PrintSubscriber<Long> printSubscriber = new PrintSubscriber<>();
        Observable.using(() -> new Object(),
                object -> {
                    System.out.println("Body " + Thread.currentThread().getName());
                    return Observable.timer(2, TimeUnit.SECONDS, Schedulers.io())
                            .doOnNext(val -> {
                                System.out.println("WOW " + Thread.currentThread().getName());
                            });
                }, obj -> {
                    System.out.println("Dispose thread " + Thread.currentThread().getName());
                })
                .subscribeOn(Schedulers.io())
                .subscribe(printSubscriber);
        printSubscriber.awaitTerminalEvent();
    }

}
