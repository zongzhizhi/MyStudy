package com.example.mvvm.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.materiatest.BR;
import com.example.materiatest.R;
import com.example.materiatest.databinding.FragmentMvvmMainBinding;
import com.example.mvvm.mvClick.FragmentMainClick;
import com.example.mvvm.bean.User;

public class MainFragment extends Fragment {

    FragmentMvvmMainBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding =   DataBindingUtil.inflate(inflater, R.layout.fragment_mvvm_main,container,false);
        User user = new User("名字","18");
//        binding.setUserNm(user);
        binding.setVariable(BR.userNm,user);
        binding.setMainCkick(new FragmentMainClick());
        getLifecycle().addObserver(new MyLifeCycleObserver());
        return binding.getRoot();
    }

    private static final String TAG = "MainFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated: ");
    }
}
