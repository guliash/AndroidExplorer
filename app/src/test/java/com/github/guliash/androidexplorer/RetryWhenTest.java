package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Single;

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
}
