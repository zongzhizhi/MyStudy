package com.example.materiatest.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.materiatest.R;
import com.example.materiatest.adapter.NavigationAdapter;
import com.example.materiatest.fragment.contentResolver.ContactResolverHelp;
import com.example.materiatest.fragment.contentResolver.ContentResolverData;
import com.example.materiatest.fragment.contentResolver.MyContentProviderFragment;

import java.util.ArrayList;
import java.util.List;

public class ContentResolverFragment extends BaseFragment implements View.OnClickListener,
        ContentResolverData {

    private Button bt_contact;
    private Button bt_customize;
    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;
    private NavigationAdapter adapter;

    private ContactResolverHelp resolverHelp;

    @Override
    protected void init(View view) {

        bt_contact = view.findViewById(R.id.bt_contact);
        bt_customize = view.findViewById(R.id.bt_customize);
        recyclerView = view.findViewById(R.id.recyclerView);

        bt_contact.setOnClickListener(this);
        bt_customize.setOnClickListener(this);

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new NavigationAdapter(null, new ArrayList<String>());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        resolverHelp = new ContactResolverHelp(this);

    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_content_resolver;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_contact:

                resolverHelp.readContacts();
                break;
            case R.id.bt_customize:

                    replaceFragment(new MyContentProviderFragment());
                break;
        }
    }

    @Override
    public void setContentResolverData(@NonNull List<String> stringList) {
        adapter.setStrings(stringList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        resolverHelp.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
