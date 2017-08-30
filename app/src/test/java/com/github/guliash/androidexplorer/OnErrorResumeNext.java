package com.github.guliash.androidexplorer;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subjects.*;
import rx.subjects.PublishSubject;

public class OnErrorResumeNext {

    @org.junit.Test
    public void test() {
        PrintSubscriber subscriber = new PrintSubscriber();
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnUnsubscribe(() -> System.out.println("Unsubscribed"))
                .flatMap(i -> {
                    if (i % 2 == 0) {
                        return Observable.just(i);
                    } else {
                        return Observable.error(new Throwable());
                    }
                })
                .retryWhen(it -> it)
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    class Bus {

        private rx.subjects.PublishSubject<Object> bus = PublishSubject.create();

        void onSomeEvent(Object object) {
            bus.onNext(object);
        }

        Observable<Object> someEvents() {
            return bus;
        }

    }

}
