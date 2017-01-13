package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Observable;
import rx.observers.TestSubscriber;

public class CombineLatestTests {

    @Test
    public void emptyObservable_notWaitsForOthers() {
        PrintSubscriber<Object> subscriber = new PrintSubscriber<>();

        Observable.combineLatest(Observable.empty(),
                Observable.just(null),
                (o1, o2) -> o1)
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

}
