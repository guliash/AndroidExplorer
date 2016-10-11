package com.github.guliash.androidexplorer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class RxOrderTest {

    private Observable<Integer> observable;
    private CountDownLatch countDownLatch;
    private Executor executor = new ThreadPoolExecutor(2, 4, 2000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    @Before
    public void setup() {
        countDownLatch = new CountDownLatch(1);
        observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                printCurrentThreadName("subscribe");
                int[] array = new int[]{1};
                for (int i = 0; i < array.length; i++) {
                    subscriber.onNext(array[i]);
                }
                subscriber.onCompleted();
            }
        });
    }

    @Test
    public void test1() {
        start(observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread()));
    }

    @Test
    public void test2() {
        start(observable.subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.newThread()));
    }

    @Test
    public void test3() {
        start(observable.subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread()));
    }

    @Test
    public void test4() {
        start(observable.subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .subscribeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .observeOn(Schedulers.newThread()));
    }

    @Test
    public void test5() {
        start(observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                }));
    }

    @Test
    public void test6() {
        start(observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                }).subscribeOn(Schedulers.io()));
    }


    @Test
    public void test7() {
        start(observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                }));
    }

    @Test
    public void test8() {
        start(observable.subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                }));
    }

    @Test
    public void test9() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                }));
    }

    @Test
    public void test10() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                }));
    }

    @Test
    public void test11() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                }));
    }

    @Test
    public void test12() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                }));
    }

    @Test
    public void test13() {
        start(observable
                .observeOn(Schedulers.computation())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                }));
    }

    @Test
    public void test14() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.computation())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                }));
    }

    @Test
    public void test15() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.computation())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .observeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                }));
    }

    @Test
    public void test16() {
        start(observable
                .subscribeOn(Schedulers.computation())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .subscribeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                }));
    }

    @Test
    public void test17() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                }));
    }

    @Test
    public void test18() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .observeOn(Schedulers.computation())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .observeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                })
                .observeOn(Schedulers.newThread()));
    }

    @Test
    public void test19() {
        start(observable
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .subscribeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                })
                .subscribeOn(Schedulers.computation()));
    }

    @Test
    public void test20() {
        start(observable
                .observeOn(Schedulers.computation())
                .map(i -> {
                    printCurrentThreadName("map1");
                    return i;
                })
                .subscribeOn(Schedulers.io())
                .map(i -> {
                    printCurrentThreadName("map2");
                    return i;
                })
                .observeOn(Schedulers.from(executor))
                .subscribeOn(Schedulers.newThread())
                .map(i -> {
                    printCurrentThreadName("map3");
                    return i;
                }));
    }

    private <T> void start(Observable<T> observable) {
        observable.subscribe(v -> printCurrentThreadName("result"),
                error -> printCurrentThreadName("result"),
                () -> countDownLatch.countDown());
    }

    private void printCurrentThreadName(String tag) {
        System.out.println(String.format("%s: %s", tag, Thread.currentThread().getName()));
    }

    @After
    public void tearDown() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
