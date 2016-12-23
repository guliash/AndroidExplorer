package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.exceptions.OnErrorNotImplementedException;
import rx.observers.TestSubscriber;

public class RepeatWhenTest {

    private PrintSubscriber<Object> subscriber = new PrintSubscriber<>();

    @Test
    public void error_doNotSubscribeToNotifications_noExceptions() {
        Observable
                .error(new Throwable())
                .repeatWhen(notifications -> notifications.take(1).doOnError(Actions.ON_ERROR_PRINT_ACTIONS))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void complete_callsNotificationsNext() {
        Observable
                .empty()
                .repeatWhen(notifications -> notifications.take(1).doOnNext(System.out::println))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void complete_notificationsOnCompletedNotCalled() {
        Observable
                .empty()
                .repeatWhen(notifications -> notifications.doOnCompleted(Actions.ON_COMPLETED_ACTION).take(2).
                            doOnNext(System.out::println))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test(expected = OnErrorNotImplementedException.class)
    public void error_subscribeToNotificationsWithoutError_throwsException() {
        Observable
                .error(new Throwable())
                .repeatWhen(notitifications -> {
                    notitifications.subscribe();
                    return Observable.timer(3, TimeUnit.SECONDS);
                })
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test(expected = OnErrorNotImplementedException.class)
    public void error_subscriberWithoutErrorImpl() {
        Observable
                .error(new Throwable())
                .repeatWhen(notifications -> notifications.take(2))
                .subscribe(v -> {});
    }

    @Test
    public void ok_repeatsWithTimerTwoTimes() {
        Observable
                .just(1, 2)
                .repeatWhen(notifications -> notifications.flatMap(el -> Observable.timer(2, TimeUnit.SECONDS))
                        .take(2).doOnCompleted(Actions.ON_COMPLETED_ACTION))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void ok_terminatesOnError() {
        Observable.just(1, 2)
                .concatWith(Observable.error(new Throwable()))
                .repeatWhen(notifications -> notifications.take(1))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void ok_repeatsOneTime() {
        //todo why prints 1 time?
        Observable.just(1, 2)
                .repeatWhen(notifications -> notifications.take(1))
                .subscribe(subscriber);

        subscriber.awaitTerminalEvent();
    }

    @Test
    public void ok_repeat_unsubscribe() {
        PrintSubscriber printSubscriber = new PrintSubscriber();
        Observable.just(1, 2)
                .doOnUnsubscribe(() -> System.out.println("Unsubscribe"))
                .repeatWhen(notifications -> notifications.flatMap(ø -> Observable.timer(1, TimeUnit.SECONDS)))
                .subscribe(printSubscriber);

        printSubscriber.awaitTerminalEvent();
    }

}
