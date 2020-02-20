package com.example.materiatest.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.materiatest.R;
import com.example.materiatest.fragment.navigationFragment;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getContenView();

    private navigationFragment navigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContenView());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = new navigationFragment();
        replaceFragment(R.id.content, navigationView);
    }

    protected void replaceFragment(@IdRes int id, Fragment contentFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .addToBackStack(null)
                .replace(id, contentFragment, contentFragment.getClass().getName())
                .commit();
    }
}
