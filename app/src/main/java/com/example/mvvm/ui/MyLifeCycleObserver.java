package com.example.mvvm.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

public class MyLifeCycleObserver implements LifecycleObserver {

    private static final String TAG = "MyLifeCycleObserver";

    public void onLifeCycleCreate(){
        Log.d(TAG, "onLifeCycleCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onLifeCycleResume(){
        Log.d(TAG, "onLifeCycleResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onLifeCycleActivityCreate() {
        Log.d(TAG, "onLifeCycleActivityCreate: ");
    }

}
