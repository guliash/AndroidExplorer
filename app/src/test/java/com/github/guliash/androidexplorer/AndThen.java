package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Completable;
import rx.Observable;
import rx.observers.TestSubscriber;

public class AndThen {

    @Test
    public void andThen__passesError() {
        TestSubscriber subscriber = new TestSubscriber();

        Completable.error(new Throwable()).andThen(Completable.complete())
                .subscribe(() -> {}, e -> {
                    e.printStackTrace();
                });

    }

    @Test
    public void test() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Observable.mergeDelayError(Observable.just(1), Observable.error(new Throwable()))
                .doOnError(it -> System.out.println("On error"))
                .onErrorResumeNext(Observable.empty())
                .buffer(2)
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

}
