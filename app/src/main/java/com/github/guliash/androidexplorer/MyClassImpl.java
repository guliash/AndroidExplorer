package com.github.guliash.androidexplorer;

import android.os.Parcel;
import android.os.Parcelable;

public class MyClassImpl implements MyClass {

    private int a;

    public MyClassImpl(int a) {
        this.a = a;
    }

    private MyClassImpl(Parcel parcel) {
        this.a = parcel.readInt();
    }

    public static final Parcelable.Creator<MyClassImpl> CREATOR = new Parcelable.Creator<MyClassImpl>() {

        @Override
        public MyClassImpl createFromParcel(Parcel source) {
            return new MyClassImpl(source);
        }

        @Override
        public MyClassImpl[] newArray(int size) {
            return new MyClassImpl[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(a);
    }

    @Override
    public int getA() {
        return a;
    }
}
