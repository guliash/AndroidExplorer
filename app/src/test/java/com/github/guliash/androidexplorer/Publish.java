package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

public class Publish {

    @Test
    public void publish_need_connect() throws InterruptedException {
        ConnectableObservable<Long> publish = Observable.interval(1000, TimeUnit.MILLISECONDS).publish();

        PrintSubscriber subscriber = new PrintSubscriber();
        publish.subscribe(subscriber);

        Subscription subscription = publish.connect();

        Thread.sleep(2000);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void publish_many_connects_not_unsubscribes() throws InterruptedException {
        ConnectableObservable<Long> publish = Observable.interval(1000, TimeUnit.MILLISECONDS).publish();

        Subscription subscription = publish.connect();

        Thread.sleep(2000);

        PrintSubscriber subscriber = new PrintSubscriber();

        publish.connect();

        publish.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void publish_disconnect() throws InterruptedException {
        ConnectableObservable<Long> connectable = Observable.interval(200, TimeUnit.MILLISECONDS).publish();
        Subscription s = connectable.connect();

        PrintSubscriber subscriber = new PrintSubscriber<Long>();
        subscriber.add(Subscriptions.create(() -> {
            System.out.println("Unsubscribe");
        }));
        connectable.subscribe(subscriber);

        Thread.sleep(1000);
        System.out.println("Closing connection");
        s.unsubscribe();

        Thread.sleep(1000);
        System.out.println("Reconnecting");
        s = connectable.connect();

        Thread.sleep(1000);

        System.out.println(subscriber.isUnsubscribed());
        Observable.combineLatest()

        Thread.sleep(5000);
        subscriber.awaitTerminalEvent();
    }

    @Test
    public void publish_overload() throws InterruptedException {
        ConnectableObservable<Long> publish = Observable.interval(1000, TimeUnit.MILLISECONDS).publish();

        Subscription subscription = publish.connect();

        Thread.sleep(2000);

        PrintSubscriber subscriber = new PrintSubscriber();

        publish.connect();

        publish.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void publish_asObservable() throws InterruptedException {
        Observable<Long> publish = Observable.interval(1000, TimeUnit.MILLISECONDS).publish().asObservable();

        PrintSubscriber subscriber = new PrintSubscriber();

        publish.subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

}
