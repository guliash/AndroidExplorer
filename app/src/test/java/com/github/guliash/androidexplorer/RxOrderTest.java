package com.github.guliash.androidexplorer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class RxOrderTest {

    private Observable<Integer> observable;
    private CountDownLatch countDownLatch;
    private Executor executor = new ThreadPoolExecutor(2, 4, 2000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    @Before
    public void setup() {
        countDownLatch = new CountDownLatch(1);
        observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                printCurrentThreadName("subscribe");
                int[] array = new int[]{1};
                for (int i = 0; i < array.length; i++) {
                    subscriber.onNext(array[i]);
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * No subscribes and observes. Will be executed on the current thread.
     */
    @Test
    public void noSubscribeAndObserve() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                }));
    }

    /**
     * Only observeOns.
     * All methods before the first (from the top) observeOn will be executed on the current thread.
     * All methods between the first and the second observeOn will be executed on the first scheduler.
     * All methods between the second and the third observeOn will be executed on the second scheduler.
     * And so on.
     */
    @Test
    public void onlyObserves() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.computation())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .observeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                })
                .observeOn(Schedulers.newThread()));
    }

    /**
     * Only subscribeOns.
     * All methods will be executed on the first from the top scheduler.
     */
    @Test
    public void onlySubscribes() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .subscribeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                })
                .subscribeOn(Schedulers.computation()));
    }

    /**
     * Both subscribeOns and observeOns.
     * All methods before the first (from the top) observeOn will be executed on the first
     * (from the top) subscribeOn's scheduler.
     * All methods between the first and the second observeOn will be executed on the
     * first(from the top) observeOn's scheduler.
     * All methods between the second and the third observeOn will be executed on the
     * second(from the top) observeOn's scheduler.
     * And so on.
     */
    @Test
    public void bothSubscribesAndObserves() {
        start(observable
                .observeOn(Schedulers.computation())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .observeOn(Schedulers.from(executor))
                .subscribeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                }));
    }

    private <T> void start(Observable<T> observable) {
        observable.subscribe(v -> printCurrentThreadName("result"),
                error -> printCurrentThreadName("result"),
                () -> countDownLatch.countDown());
    }

    private void printCurrentThreadName(String tag) {
        System.out.println(String.format("%s: %s", tag, Thread.currentThread().getName()));
    }

    @After
    public void tearDown() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
