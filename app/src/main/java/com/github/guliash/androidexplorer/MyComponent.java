package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import dagger.Subcomponent;

@Singleton
@Component(modules = MyModule.class)
public interface MyComponent {
    void inject(MainActivity activity);

    String string();
}
