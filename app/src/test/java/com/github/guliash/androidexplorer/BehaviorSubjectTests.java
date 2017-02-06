package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Observable;
import rx.Single;
import rx.subjects.BehaviorSubject;

public class BehaviorSubjectTests {

    @Test
    public void behaviorSubject_asObservable() {
        BehaviorSubject<Long> subject = BehaviorSubject.create();

        subject.onNext(1L);

        Single<Long> longs = subject.asObservable().take(1).toSingle();

        PrintSubscriber<Long> subscriber = new PrintSubscriber<>();

        longs.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

}
