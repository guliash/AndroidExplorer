package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void instanceofff() {
        List<Object> list = new ArrayList<>();

        System.out.println(list instanceof List);
    }

}
