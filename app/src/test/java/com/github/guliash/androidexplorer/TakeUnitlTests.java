package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subjects.PublishSubject;

public class TakeUnitlTests {

    @Test
    public void test() {
        final PublishSubject<Void> subject = PublishSubject.create();
        Observable.timer(1, TimeUnit.SECONDS)
                .doOnCompleted(() -> System.out.println("Completed1"))
                .takeUntil(subject)
                .doOnCompleted(() -> System.out.println("Completed2"))
                .subscribe();

        subject.onNext(null);
    }
}
