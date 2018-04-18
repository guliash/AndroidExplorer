package com.github.guliash.androidexplorer;

import org.junit.Test;

import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;

public class CompositeSubscriptionTest {

    @Test
    public void inner() {
        CompositeSubscription compositeSubscription = new CompositeSubscription();

        CompositeSubscription innerCompositeSubscription = new CompositeSubscription();

        innerCompositeSubscription.add(Subscriptions.create(() -> {}));

        compositeSubscription.add(innerCompositeSubscription);

        innerCompositeSubscription.unsubscribe();

        compositeSubscription.unsubscribe();
    }

}
