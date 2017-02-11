package com.github.guliash.androidexplorer;

import org.junit.Test;


public class Just {

    @Test
    public void just() {
        PrintSubscriber subscriber = new PrintSubscriber();

        subscriber.awaitTerminalEvent();
    }

    public int getInt() {
        System.out.println(Thread.currentThread().getName());
        return 42;
    }
}
