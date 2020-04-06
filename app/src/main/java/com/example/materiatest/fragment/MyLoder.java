package com.example.materiatest.fragment;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.CursorLoader;
import android.util.Log;

public class MyLoder extends CursorLoader {

    private Uri uri = null;
    private static final String TAG = "123";
    private Handler handler;


    public MyLoder(@NonNull Context context,Uri uri) {
        super(context,uri,null,null,null,null);
        this.uri = uri;
        handler =new Handler();
    }

    @Nullable
    @Override
    public Cursor loadInBackground() {
        Cursor cursor = super.loadInBackground();
        ContentObserver observer = new ContentObserver(handler){
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Log.i(TAG, "onChange: ");
            }
        };
//        getContext().getContentResolver().registerContentObserver(uri,true,observer);
        return cursor;
    }

    @Override
    protected void onForceLoad() {
        Log.i(TAG, "onForceLoad: ");
        super.onForceLoad();
    }
}
