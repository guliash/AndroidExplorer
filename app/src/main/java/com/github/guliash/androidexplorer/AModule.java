package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AModule {

    private String babah;

    public AModule(String babah) {
        this.babah = babah;
    }

    @Provides
    public String provideString() {
        return babah;
    }

}
