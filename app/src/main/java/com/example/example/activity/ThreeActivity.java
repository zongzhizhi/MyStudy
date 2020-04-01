package com.example.example.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.example.Interface.ActivityLifeCyle;
import com.example.materiatest.R;

public class ThreeActivity extends ActivityLifeCyle {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }
}
