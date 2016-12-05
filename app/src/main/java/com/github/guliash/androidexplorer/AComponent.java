package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = AModule.class)
public interface AComponent {

    String provideString();

}
