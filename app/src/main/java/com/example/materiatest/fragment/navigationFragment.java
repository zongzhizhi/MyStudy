package com.example.materiatest.fragment;

import android.app.Notification;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.example.materiatest.R;
import com.example.materiatest.adapter.NavigationAdapter;
import com.example.mvvm.ui.MainFragment;

import java.util.ArrayList;
import java.util.List;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE;

public class navigationFragment extends BaseFragment implements NavigationAdapter.OnAdapterClickLis {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private NavigationAdapter adapter;
    private List<String> strings;

    @Override
    protected void init(View view) {
        AddStrings();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new NavigationAdapter(this, strings);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void AddStrings() {
        strings = new ArrayList<>();
        strings.add("自定义View");
        strings.add("数据库");
        strings.add("内容提供者");
        strings.add("DataBinding学习");
        strings.add("通知");
        strings.add("DiaLog");
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void onAdapterClick(View view, int position) {
        switch (position) {
            case 0:
                replaceFragment(new customViewFragment());
                break;
            case 1:
                replaceFragment(new DatabaseFragment());
                break;
            case 2:
                replaceFragment(new ContentResolverFragment());
                break;
            case 3:
                replaceFragment(new MainFragment());
                break;
            case 4:
                replaceFragment(new NotificationFragment());
                break;
            case 5:

                break;
        }
    }
}
