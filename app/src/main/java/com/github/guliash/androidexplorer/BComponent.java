package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Component;

@Component(dependencies = {AComponent.class})
public interface BComponent {

    String provideString();
}
