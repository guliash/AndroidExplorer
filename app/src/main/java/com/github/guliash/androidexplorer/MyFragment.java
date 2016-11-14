package com.github.guliash.androidexplorer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFragment extends BaseFragment {

    public static RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        List<String> strings = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            strings.add(Integer.toString(i));
        }

        pool.setMaxRecycledViews(0, 10);

        Adapter adapter = new Adapter(strings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setRecycledViewPool(pool);
        recyclerView.setItemViewCacheSize(0);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG", String.format("HOLDER %s", pool.getRecycledView(0)));
    }

    private static class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        private List<String> strings;

        public Adapter(List<String> stringList) {
            strings = stringList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.view.setText(strings.get(position));
            Log.e("TAG", String.format("HOLDER %s", pool.getRecycledView(0)));
        }


        @Override
        public int getItemCount() {
            return strings.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            private TextView view;

            public ViewHolder(View itemView) {
                super(itemView);

                view = (TextView) itemView;
            }
        }
    }
}
