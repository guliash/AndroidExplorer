package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Completable;
import rx.Observable;
import rx.observers.TestSubscriber;

public class AndThen {

    @Test
    public void andThen__passesError() {
        TestSubscriber subscriber = new TestSubscriber();
        Completable.error(new Throwable()).andThen(Completable.complete())
                .subscribe(() -> {}, e -> {
                    e.printStackTrace();
                });

    }

}
