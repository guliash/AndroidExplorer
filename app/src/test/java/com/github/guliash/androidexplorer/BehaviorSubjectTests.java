package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Single;
import rx.Subscription;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

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

    static Subscription subscription1;
    static Subscription subscription2;
    @Test
    public void behaviorSubjectEmits_afterUnsubscribe() {

        final BehaviorSubject<Integer> subject = BehaviorSubject.create();

        subscription1 = subject.subscribe(i -> {
            subscription2.unsubscribe();
            System.out.println("first: " + i);
        });

        subscription2 = subject.subscribe(i -> {
            System.out.println("second: " + i);
        });

        subject.onNext(1);
        subject.onNext(2);

    }

}
