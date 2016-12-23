package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;

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

}
