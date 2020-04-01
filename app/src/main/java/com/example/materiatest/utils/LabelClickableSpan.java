package com.example.materiatest.utils;

import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class LabelClickableSpan extends ClickableSpan {
    private static final String TAG = "LabelClickableSpan";

    private String add;
    public LabelClickableSpan(String add){
        this.add = add;
    }

    @Override
    public void onClick(View widget) {
        Log.i(TAG, "onClick: " + add);
        Toast.makeText(widget.getContext(),add,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}
