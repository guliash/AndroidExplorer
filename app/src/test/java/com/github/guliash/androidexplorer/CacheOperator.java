package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

public class CacheOperator {
    @Test
    public void doesNotSubscribeTwice() {
        final Single<Long> source = Single.timer(1, TimeUnit.SECONDS).doOnSubscribe(disposable -> {
            System.out.println("Subscribe");
        });

        final Single<Long> cachedSource = source.cache();

        cachedSource.subscribe(it -> System.out.println("Result1"));
        cachedSource.subscribe(it -> System.out.println("Result2"));

        cachedSource.test().awaitTerminalEvent();
    }
}
