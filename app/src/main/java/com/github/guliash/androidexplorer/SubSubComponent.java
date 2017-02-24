package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

@SubSubScope
@Subcomponent(modules = MyModule.class)
public interface SubSubComponent {
    void inject(MainActivity activity);
}
