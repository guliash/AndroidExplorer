package com.github.guliash.androidexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    void click() {
        Intent intent = new Intent(this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("KEY", new MyObject(null, -1d));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public static class MyObject implements Parcelable {

        Integer intValue;
        Double doubleValue;

        public MyObject(Integer i, Double d) {
            this.intValue = i;
            this.doubleValue = d;
        }

        protected MyObject(Parcel in) {
            Log.e(BaseActivity.TAG, "FROM PARCEL");
            intValue = (Integer)in.readValue(Integer.class.getClassLoader());
            doubleValue = (Double)in.readValue(Double.class.getClassLoader());

        }

        public static final Creator<MyObject> CREATOR = new Creator<MyObject>() {
            @Override
            public MyObject createFromParcel(Parcel in) {
                return new MyObject(in);
            }

            @Override
            public MyObject[] newArray(int size) {
                return new MyObject[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(intValue);
            dest.writeValue(doubleValue);
        }
    }
}
