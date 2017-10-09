package com.github.guliash.androidexplorer;

import org.junit.*;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subjects.PublishSubject;

public class PublishSubjectTest {

    @org.junit.Test
    public void error() {
        rx.subjects.PublishSubject<Void> subject = rx.subjects.PublishSubject.create();

        subject.subscribe(new PrintSubscriber<Void>());

        subject.onError(new Throwable());

        subject.onNext(null);
    }

    @Test
    public void moreThanOneSubscription() throws InterruptedException {
        PublishSubject<Long> subject = PublishSubject.create();

        Observable.interval(1, TimeUnit.SECONDS).take(5).subscribe(subject);

        Observable.interval(1, TimeUnit.SECONDS).take(5).subscribe(subject);

        final PrintSubscriber subscriber = new PrintSubscriber();

        subject.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

}
