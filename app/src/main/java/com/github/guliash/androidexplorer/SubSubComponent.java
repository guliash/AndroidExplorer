package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent
public interface SubSubComponent {
    void inject(MainActivity activity);
}
