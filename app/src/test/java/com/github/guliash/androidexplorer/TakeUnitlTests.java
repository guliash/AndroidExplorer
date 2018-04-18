package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.BackpressureOverflow;
import rx.Observable;
import rx.Subscriber;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class TakeUnitlTests {

    @Test
    public void test() {
        final PublishSubject<Void> subject = PublishSubject.create();

        final ConnectableObservable<Void> observable = subject.replay(1);

        observable.connect();

        subject.onNext(null);

        observable.subscribe(it -> System.out.println(it));
    }
}
