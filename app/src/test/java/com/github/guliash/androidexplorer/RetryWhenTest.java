package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class RetryWhenTest {

    @Test
    public void retryWhen_justOutput() {
        PrintSubscriber printSubscriber = new PrintSubscriber();
        Observable.error(new Throwable())
                .doOnError(v -> System.out.println("retry"))
                .retryWhen(errors -> errors.flatMap(el -> Observable.timer(1, TimeUnit.SECONDS)).take(2))
                .subscribe(printSubscriber);

        printSubscriber.awaitTerminalEvent();
    }

    @Test
    public void retryWhen_single_throwsExceptionsIfCompletedNotification() {
        PrintSubscriber printSubscriber = new PrintSubscriber();
        Single.error(new Throwable())
                .retryWhen(errors -> Observable.empty())
                .subscribe(printSubscriber);

        printSubscriber.awaitTerminalEvent();
        printSubscriber.assertError(NoSuchElementException.class);
    }

    @Test
    public void retryWhen_single_retries() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Single.error(new Throwable())
                .doOnError(v -> System.out.println("error"))
                .retryWhen(errors -> errors.take(2))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void retryWhen_handlerError() {
        PrintSubscriber printSubscriber = new PrintSubscriber();
        Observable.error(new Throwable())
                .doOnError(v -> System.out.println("retry"))
                .retryWhen(errors -> Observable.error(new Throwable()))
                .subscribe(printSubscriber);

        printSubscriber.awaitTerminalEvent();
    }

    @Test
    public void retryWhen_checkObserveOn() {
        PrintSubscriber printSubscriber = new PrintSubscriber();

        Observable.error(new Throwable())
                .observeOn(Schedulers.io())
                .retryWhen(errors -> {
                    System.out.println(Thread.currentThread().getName());
                    return Observable.empty();
                })
                .subscribeOn(Schedulers.computation())
                .unsubscribeOn(Schedulers.io())
                .subscribe(printSubscriber);
        printSubscriber.add(Subscriptions.create(() -> {
            System.out.println("unsbuscribe");
            System.out.println(Thread.currentThread().getName());
        }));
        printSubscriber.awaitTerminalEvent();
    }

    @Test
    public void retryWhen_unsubscribeTest() {
        PrintSubscriber printSubscriber = new PrintSubscriber();
        Observable.just(1)
                .doOnUnsubscribe(() -> System.out.println("Unsubscribed 1"))
                .doOnSubscribe(() -> System.out.println("Subscribed 1"))
                .flatMap(i -> Observable.error(new Throwable()))
                .doOnUnsubscribe(() -> System.out.println("Unsubscribe 2"))
                .doOnSubscribe(() -> System.out.println("Subscribed 2"))
                .retryWhen(errors -> errors.take(3).doOnNext(th -> System.out.println(th)))
                .subscribe(printSubscriber);

        printSubscriber.awaitTerminalEvent();
    }

    @Test
    public void retryWhenUpStreamNoError() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Observable.just(1, 2, 3, 4)
                .doOnNext(i -> System.out.println("Next " + i))
                .doOnUnsubscribe(() -> System.out.println("Unsubscribe"))
                .flatMap(i -> {
                    return Observable.error(new Throwable());
                })
                .onErrorResumeNext(error -> {
                    return Observable.just(1);
                })
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void retryWhenWithoutComposingErrors() {
        TestSubscriber subscriber = new TestSubscriber();

        Observable.just(1)
                .retryWhen(errors -> Observable.never())
                .subscribe(subscriber);

        subscriber.assertCompleted();
    }

    @Test
    public void retryWhenWithoutComposingErrorsSingle() {
        PrintSubscriber subscriber = new PrintSubscriber();

        Single.just(1)
                .doOnSuccess(it -> System.out.println("success " + it))
                .doOnSubscribe(() -> System.out.println("subscribe "))
                .retryWhen(errors -> Observable.never())
                .doOnSuccess(it -> System.out.println("success after " + it))
                .doOnError(it -> System.out.println("error"))
                .doOnSuccess(it -> System.out.println("Do on next " + it))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }
}
