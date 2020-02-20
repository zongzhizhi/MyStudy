package com.example.materiatest.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.materiatest.R;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentView(), container, false);
        init(view);
        return view;
    }

    protected abstract void init(View view);

    protected abstract  @LayoutRes int getFragmentView();

    protected void replaceFragment(Fragment contentFragment) {
        replaceFragment(R.id.content,contentFragment);
    }

    protected void replaceFragment(@IdRes int id, Fragment contentFragment) {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(id, contentFragment, contentFragment.getClass().getName())
                .commit();
    }
}
