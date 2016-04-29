package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        test2();
    }

    static class A {

        String s;
        public A(String s) {
            this.s = s;
        }

        public static A parse(String s) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            } finally {
                return new A(s);
            }
        }

        @Override
        public String toString() {
            return s;
        }
    }

    public void test() {

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                int size = 10;
                Log.e(TAG, "SUBSCRIBE THREAD NAME " + Thread.currentThread().getName());
                for(int i = 0; i < size; i++) {
                    subscriber.onNext(String.valueOf(i));
                }
                subscriber.onCompleted();
            }
        }).map(new Func1<String, A>() {
            @Override
            public A call(String s) {
                Log.e(TAG, "MAP THREAD NAME " + Thread.currentThread().getName());
                return A.parse(s);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<A>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "COMPLETED");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(A a) {
                Log.e(TAG, "NEXT THREAD NAME " + Thread.currentThread().getName());
                Log.e(TAG, "NEXT " + a);
            }
        });
    }

    public void test2() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectableObservable< Long > observable = Observable.interval(1, TimeUnit.SECONDS).publish();
                observable.connect();
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {

                }
                observable.subscribe(new Observer<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.e(TAG, "BOOM " + aLong);
                    }
                });
            }
        }).start();

    }
}
