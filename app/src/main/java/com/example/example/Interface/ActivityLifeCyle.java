package com.example.example.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class ActivityLifeCyle extends AppCompatActivity implements BaseLifeCycle {


    private static final String TAG = "ActivityLifeCyle";

    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, getClass().getName() + "---" + "onRestart: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, getClass().getName() + "---" + "onCreate: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, getClass().getName() + "---" + "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, getClass().getName() + "---" + "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, getClass().getName() + "---" + "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, getClass().getName() + "---" + "onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, getClass().getName() + "---" + "onDestroy: ");
    }
}
