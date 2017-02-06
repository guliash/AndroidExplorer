package com.github.guliash.androidexplorer;

import org.junit.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import rx.Observable;
import rx.observables.AsyncOnSubscribe;
import rx.observables.SyncOnSubscribe;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

public class Backpressure {

    @org.junit.Test
    public void sync() {
        TestSubscriber<Integer> subscriber = new TestSubscriber<Integer>() {
            @Override
            public void onNext(Integer i) {
                super.onNext(i);
                System.out.println("here");
                request(1);
            }
        };
        Observable.<Integer>create(SyncOnSubscribe.createStateful(() -> 0,
                (counter, observer) -> {
                    System.out.println(counter);
                    if (counter < 100) {
                        observer.onNext(counter);
                    } else {
                        observer.onCompleted();
                    }
                    return ++counter;
                })).subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @org.junit.Test
    public void async() {
        AtomicLong next = new AtomicLong(1);
        TestSubscriber<Long> subscriber = new TestSubscriber<Long>() {

            @Override
            public void onStart() {
                request(next.getAndIncrement());
            }

            @Override
            public void onNext(Long i) {
                super.onNext(i);
                System.out.println("here");
                request(next.getAndIncrement());
            }
        };
        Observable.<Long>create(AsyncOnSubscribe.createStateful(
                () -> 0L,
                (counter, requested, observer) -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(counter + " " + requested);
                    if (counter < 100) {
                        Long finalCounter = counter;
                        observer.onNext(Observable.interval(1, TimeUnit.SECONDS)
                                .take(requested.equals(Long.MAX_VALUE) ? 1 : requested.intValue())
                                .doOnNext(i -> {
                                    System.out.println("inner " + Thread.currentThread().getName());
                                    System.out.println("next " + requested.intValue());
                                })
                                .doOnSubscribe(() -> {
                                    System.out.println("Subscribe " + finalCounter);
                                })
                                .subscribeOn(Schedulers.computation()));
                    } else {
                        observer.onCompleted();
                    }
                    return ++counter;
                }))
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @org.junit.Test
    public void sync2() {
        TestSubscriber<Integer> subscriber = new TestSubscriber<Integer>() {
            @Override
            public void onNext(Integer i) {
                super.onNext(i);
                System.out.println("here");
            }
        };
        Observable.<Integer>create(SyncOnSubscribe.createStateful(() -> 0,
                (counter, observer) -> {
                    System.out.println(counter);
                    if (counter < 100) {
                        observer.onNext(counter);
                    } else {
                        observer.onCompleted();
                    }
                    return ++counter;
                })).subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void sync3() {

    }

}
