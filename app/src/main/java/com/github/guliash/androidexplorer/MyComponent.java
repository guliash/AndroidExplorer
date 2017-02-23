package com.github.guliash.androidexplorer;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

@Singleton
@Component(modules = MyModule.class)
public interface MyComponent {

    SubComponent plus();


}
