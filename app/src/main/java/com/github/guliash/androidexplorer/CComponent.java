package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Component;

@Component(dependencies = BComponent.class)
public interface CComponent {
    void inject(MainActivity mainActivity);

    String provideString();
}
