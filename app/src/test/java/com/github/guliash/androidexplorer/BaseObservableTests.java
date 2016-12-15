package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Observable;
import rx.exceptions.OnErrorNotImplementedException;

public class BaseObservableTests {

    @Test
    public void errorOnNext_errorGoesToOnError() {
        Observable.just(1)
                .subscribe(v -> {
                    throw new RuntimeException();
                }, System.out::println);
    }

    @Test(expected = OnErrorNotImplementedException.class)
    public void error_withoutOnError() {
        Observable.error(new Throwable())
                .subscribe();
    }

}
