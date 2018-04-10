package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public class Valve {

    @Test
    public void valve() {
        final PrintSubscriber<Long> printSubscriber = new PrintSubscriber<>();
        final Random random = new Random();
        final Observable<Boolean> gates = Observable.interval(10, TimeUnit.SECONDS)
                .map(it -> random.nextBoolean())
                .startWith(false)
                .doOnNext(it -> System.out.println("Gate " + (it ? "opened" : "closed")))
                .take(100);
        final Observable<Long> data = Observable.interval(3, TimeUnit.SECONDS)
                .doOnNext(it -> System.out.println("New value " + it))
                .take(100);

        gates.publish(innerGates -> data.publish(innerData -> Observable.merge(
                innerData.window(
                        innerGates.distinctUntilChanged().filter(it -> it),
                        (ignore -> innerGates.distinctUntilChanged().filter(it -> !it))
                ).flatMap(it -> it),
                innerData.buffer(
                        innerGates.distinctUntilChanged().filter(it -> !it),
                        (ignore -> innerGates.distinctUntilChanged().filter(it -> it))
                )
                        .flatMap(Observable::from)
        )))
                .subscribe(it -> System.out.println("On next " + it));

        printSubscriber.awaitTerminalEvent();
    }
}
