package com.github.guliash.androidexplorer;

import dagger.Component;
import dagger.Subcomponent;

@Subcomponent
@SubScope
public interface SubComponent {
    SubSubComponent plus();
}
