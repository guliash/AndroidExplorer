package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    @BindView(R.id.pager)
    ViewPager pager;

    private MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Timber.plant(new Timber.DebugTree());

        ButterKnife.bind(this);

        adapter = new MyPagerAdapter(randomStrings());
        pager.setAdapter(adapter);

    }

    @OnClick(R.id.button)
    void onBtnClick() {

        List<String> strings = randomStrings();
        Timber.d("CLICK %s", strings);
        adapter.setStrings(strings);
        adapter.notifyDataSetChanged();
//
//        for(int i = 0; i < strings.size(); i++) {
//            View view = pager.findViewWithTag(i);
//            if(view != null) {
//                adapter.updateView(view, strings.get(i));
//            }
//        }
    }

    @OnClick(R.id.clear)
    void onClearClick() {
        adapter.setStrings(new ArrayList<>());
        adapter.notifyDataSetChanged();
    }

    private List<String> randomStrings() {
        Random random = new Random();
        List<String> strings = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            strings.add(String.valueOf(random.nextInt(2)));
        }
        return strings;
    }

    private static class MyPagerAdapter extends PagerAdapter {

        private List<String> strings;

        public MyPagerAdapter(final @NonNull List<String> strings) {
            this.strings = new ArrayList<>(strings);
        }

        public void setStrings(final @NonNull List<String> strings) {
            this.strings = new ArrayList<>(strings);
        }

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Timber.d("INSTANTIATE ITEM %d", position);
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            View view = inflater.inflate(R.layout.my_layout, container, false);
            view.setTag(position);
            updateView(view, strings.get(position));
            container.addView(view);
            return view;
        }

        public void updateView(View view, String string) {
            TextView tv = (TextView)view.findViewById(R.id.text);
            tv.setText(string);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Timber.d("DESTROY ITEM %d", position);
            container.removeView((View)object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }
}
