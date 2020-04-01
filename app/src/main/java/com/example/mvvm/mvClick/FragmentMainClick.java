package com.example.mvvm.mvClick;

import android.view.View;
import android.widget.Toast;

import com.example.materiatest.R;

public class FragmentMainClick {

    public void onclick(View view){
        switch (view.getId()){
            case R.id.bt_userName:
                Toast.makeText(view.getContext(),"点击了",Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void LongClick(View view){
        Toast.makeText(view.getContext(),"长点击了",Toast.LENGTH_LONG).show();
    }
}
