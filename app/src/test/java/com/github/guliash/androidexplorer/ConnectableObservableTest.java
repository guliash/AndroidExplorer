package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.Observable;

public class ConnectableObservableTest {

    @Test
    public void test() {
        Observable.error(new Throwable()).publish().connect();
    }

}
