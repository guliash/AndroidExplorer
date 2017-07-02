package com.github.guliash.androidexplorer;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ArrayList<MyClass> myClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            myClass = savedInstanceState.getParcelableArrayList("KEY");
        } else {
            myClass = new ArrayList<>(Collections.singletonList(new MyClassImpl(44)));
        }

        System.out.println("AFTER " + myClass.get(0).getA());
        System.out.println("AFTER " + myClass);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("KEY", myClass);
    }
}
