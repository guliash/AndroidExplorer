package com.github.guliash.androidexplorer;

import org.junit.*;

public class PublishSubject {

    @org.junit.Test
    public void error() {
        rx.subjects.PublishSubject<Void> subject = rx.subjects.PublishSubject.create();

        subject.subscribe(new PrintSubscriber<Void>());

        subject.onError(new Throwable());

        subject.onNext(null);
    }

}
