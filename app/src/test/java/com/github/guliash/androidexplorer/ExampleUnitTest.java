package com.github.guliash.androidexplorer;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    public static class A {
        Observable<Long> longs = Observable.interval(1000, TimeUnit.MILLISECONDS, Schedulers.immediate());

        public void bind(Subscriber<Long> subscriber) {
            longs.subscribe(subscriber);
        }
    }

    public static class B {

        public void run(A a) {
            a.bind(new Subscriber<Long>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Long aLong) {
                    System.out.println(aLong);
                }
            });
        }
    }

    @Test
    public void test() {
        B b = new B();
        b.run(new A());
        b = null;

        while(true){
            System.gc();
        }
    }

    @Test
    public void subscriberDoesntLeak() {

        class ShortLivedContainer {
            private final Observable<Void> activitySource;

            public ShortLivedContainer(Observable<Void> activitySource) {
                this.activitySource = activitySource;
            }

            public void subscribeToShit() {
                activitySource.subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {
                        System.out.println("boom");
                    }
                });
            }

        }

        final Observable<Void> longLivedActivity = Observable.interval(10, TimeUnit.MILLISECONDS).map(new Func1<Long, Void>() {
            @Override
            public Void call(Long aLong) {
                return null;
            }
        });

        ShortLivedContainer shortLivedContainer = new ShortLivedContainer(longLivedActivity);
        shortLivedContainer.subscribeToShit();

        WeakReference<ShortLivedContainer> gcSpy = new WeakReference<>(shortLivedContainer);

        shortLivedContainer = null;

        System.gc();

//        while (true) {
//
//        }

        assertTrue(gcSpy.get() == null);

    }

}