package com.github.guliash.androidexplorer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class RxOrderTest {

    private Observable<Integer> observable;

    @Before
    public void setup() {
        observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                printCurrentThreadName("subscribe");
                int[] array = new int[] {1};
                for(int i = 0; i < array.length; i++) {
                    subscriber.onNext(array[i]);
                }
                subscriber.onCompleted();
            }
        });
    }

    @Test
    public void test1() {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())
                .subscribe(v -> {
                    printCurrentThreadName("observer");
                }, error -> {
                    printCurrentThreadName("observe");
                });
    }

    private void printCurrentThreadName(String tag) {
        System.out.println(String.format("%s: %s", tag, Thread.currentThread().getName()));
    }

}
