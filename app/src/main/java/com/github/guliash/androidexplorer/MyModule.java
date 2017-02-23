package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {

    @Singleton
    @Provides
    public String getString() {
        return "2";
    }
}
