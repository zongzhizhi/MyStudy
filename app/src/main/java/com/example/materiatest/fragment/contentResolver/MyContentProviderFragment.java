package com.example.materiatest.fragment.contentResolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.materiatest.R;
import com.example.materiatest.fragment.BaseFragment;
import com.example.materiatest.fragment.MyLoder;

public class MyContentProviderFragment extends BaseFragment implements View.OnClickListener {

    private static final String URI = "content://com.example.materiatest.provider/book";

    private Button bt_Add;
    private Button bt_delete;
    private Button bt_updata;
    private Button bt_query;
    private Button bt_loader;
    private TextView et_content;

    private ContentResolver resolver;
    private Uri uri;

    @Override
    protected void init(View view) {
        bt_Add = view.findViewById(R.id.bt_Add);
        bt_delete = view.findViewById(R.id.bt_delete);
        bt_updata = view.findViewById(R.id.bt_updata);
        bt_query = view.findViewById(R.id.bt_query);
        et_content = view.findViewById(R.id.et_content);
        bt_loader = view.findViewById(R.id.bt_loader);

        bt_Add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_updata.setOnClickListener(this);
        bt_query.setOnClickListener(this);
        bt_loader.setOnClickListener(this);

        resolver = getActivity().getContentResolver();
        uri = Uri.parse(URI);
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_my_cotent_provider;
    }

    @Override
    public void onClick(View v) {

        ContentValues values = new ContentValues();
        switch (v.getId()) {
            case R.id.bt_Add:

                values.put("name", "第一本书");
                values.put("price", 0.01);
                values.put("pages", 200);
                resolver.insert(uri, values);
//                queryData();
                break;
            case R.id.bt_delete:
                resolver.delete(uri, "price = ?", new String[]{"0.01"});
//                queryData();
                break;
            case R.id.bt_updata:
                values.put("name", "书名更新了！");
                resolver.update(uri, values, "name = ?", new String[]{"第一本书"});
//                queryData();
                break;
            case R.id.bt_query:

                queryData();
                break;
            case R.id.bt_loader:

                setContentResourNotifia();
                break;
        }
    }

    private void queryData() {
        Cursor cursor = resolver.query(uri, null, null, null, null);
        StringBuilder builder = new StringBuilder();
        if (cursor != null) {

            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String pages = cursor.getString(cursor.getColumnIndex("pages"));
                builder.append("name = ")
                        .append(name)
                        .append(" price = ")
                        .append(price)
                        .append(" pages = ")
                        .append(pages)
                        .append("\n");
            }
            cursor.close();
        }

        et_content.setText(builder.toString());
    }
    private int i = 0;
    private static final String TAG = "123";

    private void setContentResourNotifia() {

        final String URI = "content://com.example.materiatest.provider/book";
        getLoaderManager().initLoader(0, getArguments(),
                new LoaderManager.LoaderCallbacks<Cursor>() {
            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
                Log.i(TAG, "onCreateLoader: ");


                return new MyLoder(getContext(),Uri.parse(URI));
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                i=0;
                Log.i(TAG, "onLoadFinished: " + i);
                StringBuilder builder = new StringBuilder();
                if (cursor != null && !cursor.isClosed()) {

                    while (cursor.moveToPosition(i++)) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String price = cursor.getString(cursor.getColumnIndex("price"));
                        String pages = cursor.getString(cursor.getColumnIndex("pages"));
                        builder.append("name = ")
                                .append(name)
                                .append(" price = ")
                                .append(price)
                                .append(" pages = ")
                                .append(pages)
                                .append("\n");
                    }
                    et_content.setText(builder.toString());
                }


            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {
                Log.i(TAG, "onLoaderReset: ");
            }
        });
    }
}
