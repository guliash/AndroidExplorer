package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.subjects.PublishSubject;

public class Delay {

    @Test
    public void delayingSubscription() {
        TestSubscriber<Long> subscriber = new TestSubscriber<>();
        Observable.<Long>create((inSubscriber -> {
            System.out.println("Subscribe");
            inSubscriber.onCompleted();
        })).delay(() -> Observable.timer(5, TimeUnit.SECONDS), Observable::just)
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void delayingSubscription_emitting() {
        PrintSubscriber<Long> subscriber = new PrintSubscriber<>();
        Observable.<Long>create((inSubscriber -> {
            System.out.println("Subscribe");
            for(int i = 0; i < 100; i++) {
                inSubscriber.onNext((long)i);
            }
            inSubscriber.onCompleted();
        })).delay(() -> Observable.timer(5, TimeUnit.SECONDS), Observable::just)
                .subscribe(subscriber);
        subscriber.awaitTerminalEvent();
    }

    @Test
    public void delay__notDelayingSubscription() {
        TestSubscriber<Long> subscriber = new TestSubscriber<>();
        Observable.<Long>create((inSubscriber -> {
            System.out.println("Subscribe");
            inSubscriber.onCompleted();
        })).delay(5, TimeUnit.SECONDS)
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();

    }

    @Test
    public void delay__errorsNotDelayed() {
        TestSubscriber<Long> subscriber = new TestSubscriber<>();
        Observable.<Long>create((inSubscriber -> {
            System.out.println("Subscribe");
            inSubscriber.onError(new Throwable());
        })).delay(5, TimeUnit.SECONDS)
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }
}
