package com.example.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.example.Interface.ActivityLifeCyle;
import com.example.materiatest.R;

public class OneLifeActivity extends ActivityLifeCyle {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        findViewById(R.id.bt_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),TwoActivty.class);
                startActivity(intent);
            }
        });
    }


}
