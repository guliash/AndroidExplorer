package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.ArrayList;

import rx.Observable;

public class Reduce {

    @Test
    public void completable_reduce() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Observable.empty()
                .reduce(new ArrayList<>(), (list, model) -> {
                    list.add(model);
                    return list;
                })
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

}
