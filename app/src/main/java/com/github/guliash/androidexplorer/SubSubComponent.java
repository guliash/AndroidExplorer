package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

@SubSubScope
@Subcomponent
public interface SubSubComponent {
    void inject(MainActivity activity);

    @Subcomponent.Builder
    interface Builder {
        SubSubComponent build();
    }
}
