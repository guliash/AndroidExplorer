package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

public class ThrottleFirst {

    @Test
    public void check() throws InterruptedException {
        PrintSubscriber printSubscriber = new PrintSubscriber();
        CountDownLatch latch = new CountDownLatch(4);
        Observable.interval(1000, TimeUnit.MILLISECONDS).throttleFirst(500, TimeUnit.MILLISECONDS)
                .take(4)
                .subscribe(v -> {
                    System.out.println(Thread.currentThread().getName());
                    latch.countDown();
                });
        latch.await();
    }



    @Test
    public void isSchedulerPersisted() {
        TestSubscriber subscriber = new TestSubscriber();
        System.out.println(Thread.currentThread().getName());
        Observable.interval(200, TimeUnit.MILLISECONDS)
                .take(10)
                .delay(10, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .throttleWithTimeout(100, TimeUnit.MILLISECONDS)
                .doOnNext(v -> System.out.println(Thread.currentThread().getName()))
                .doOnUnsubscribe(() -> System.out.println("On unsubscribe"))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    public Observable<Long> rec() {
        return Observable.defer(() -> Observable.interval(100, TimeUnit.MILLISECONDS).take(2)
                .concatWith(Observable.defer(() -> rec())));
    }

}
