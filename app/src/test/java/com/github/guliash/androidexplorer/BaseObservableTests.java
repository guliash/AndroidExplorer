package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Observable;
import rx.exceptions.OnErrorNotImplementedException;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class BaseObservableTests {

    @Test
    public void errorOnNext_errorGoesToOnError() {
        Observable.just(1)
                .subscribe(v -> {
                    throw new RuntimeException();
                }, System.out::println);
    }

    @Test
    public void error_inOnError() {
        Observable.error(new Throwable())
                .subscribe(v -> {
                }, error -> {
                    int x = 1 / 0;
                });

    }

    @Test(expected = OnErrorNotImplementedException.class)
    public void error_withoutOnError() {
        Observable.error(new Throwable())
                .subscribe();
    }

    @Test
    public void unsubscribeOnSameThreadAsSubscribe() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Observable.error(new Throwable())
                .retryWhen(s -> Observable.empty())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);

        subscriber.add(Subscriptions.create(() -> {
            System.out.println(Thread.currentThread().getName());
        }));

        subscriber.awaitTerminalEvent();
    }

}
