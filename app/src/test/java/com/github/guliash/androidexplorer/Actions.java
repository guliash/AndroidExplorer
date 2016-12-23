package com.github.guliash.androidexplorer;

import rx.functions.Action0;
import rx.functions.Action1;

public class Actions {

    public static final Action1<Throwable> ON_ERROR_PRINT_ACTIONS = throwable ->
            System.out.println("Error action " + throwable);

    public static final Action0 ON_COMPLETED_ACTION = () -> System.out.println("On completed action");

}
