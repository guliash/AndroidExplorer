package com.github.guliash.androidexplorer;

import rx.observers.TestSubscriber;

public class PrintSubscriber<T> extends TestSubscriber<T> {

    @Override
    public void onNext(T t) {
        super.onNext(t);
        System.out.println("on next " + t);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        System.out.println("on error");
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        System.out.println("on completed");
    }
}
