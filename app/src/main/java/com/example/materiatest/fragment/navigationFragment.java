package com.example.materiatest.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.materiatest.R;
import com.example.materiatest.adapter.NavigationAdapter;

import java.util.ArrayList;
import java.util.List;

public class navigationFragment extends BaseFragment implements NavigationAdapter.OnAdapterClickLis {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private NavigationAdapter adapter;
    private List<String> strings;

    @Override
    protected void init(View view) {
        strings = new ArrayList<>();
        strings.add("自定义View");
        strings.add("数据库");
        strings.add("内容提供者");
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new NavigationAdapter(this, strings);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void onAdapterClick(View view, int position) {
        switch (position) {
            case 0:
                replaceFragment(R.id.content, new customViewFragment());
                break;
            case 1:
                replaceFragment(R.id.content, new DatabaseFragment());
                break;
            case 2:
                replaceFragment(R.id.content, new ContentResolverFragment());
                break;
        }
    }
}
