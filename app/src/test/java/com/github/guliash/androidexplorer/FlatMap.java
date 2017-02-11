package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Notification;
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


    @Test
    public void test() {

        PrintSubscriber<Object> subscriber = new PrintSubscriber<>();

        Observable.concat(Observable.just(1), Observable.empty())
                .materialize().subscribe(subscriber);

        Observable.error(new Throwable())
                .materialize()
                .doOnNext(ø -> System.out.println("doOnNext " + Thread.currentThread().getName()))
                .flatMap(notification -> {
                    if (notification.getKind() == Notification.Kind.OnNext) {
                        return Observable.just(notification.getValue());
                    }
                    if (notification.getKind() == Notification.Kind.OnError) {
                        return Observable.just(1)
                                .doOnNext(v -> System.out.println("Inner onNext " + Thread.currentThread().getName()))
                                .observeOn(Schedulers.computation())
                                .doOnSubscribe(() -> {
                                    System.out.println("Ui here " + Thread.currentThread().getName());
                                });
                    }
                    return Observable.empty();
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(value -> System.out.println("Outer doOnNext " + Thread.currentThread().getName()))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void test1() {
        PrintSubscriber printSubscriber = new PrintSubscriber();
        Observable.just(1, 2)
                .observeOn(Schedulers.io())
                .flatMap(i -> Observable.just(1, 2).observeOn(Schedulers.computation()))
                .doOnNext(ø -> System.out.println("current thread " + Thread.currentThread().getName()))
                .subscribe(printSubscriber);
    }

}
