package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

@Component(dependencies = MyComponent.class)
@SubScope
public interface SubComponent {
    void inject(MainActivity activity);
}
