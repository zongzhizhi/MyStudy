package com.example.materiatest.fragment.contentResolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.materiatest.R;
import com.example.materiatest.fragment.BaseFragment;

public class MyContentProviderFragment extends BaseFragment implements View.OnClickListener {

    private static final String URI = "content://com.example.materiatest.provider/book";

    private Button bt_Add;
    private Button bt_delete;
    private Button bt_updata;
    private Button bt_query;
    private EditText et_content;

    private ContentResolver resolver;
    private Uri uri;

    @Override
    protected void init(View view) {
        bt_Add = view.findViewById(R.id.bt_Add);
        bt_delete = view.findViewById(R.id.bt_delete);
        bt_updata = view.findViewById(R.id.bt_updata);
        bt_query = view.findViewById(R.id.bt_query);
        et_content = view.findViewById(R.id.et_content);

        bt_Add.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_updata.setOnClickListener(this);
        bt_query.setOnClickListener(this);

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
                queryData();
                break;
            case R.id.bt_delete:
                resolver.delete(uri,"name = ?",new String[]{"标题"});
                queryData();
                break;
            case R.id.bt_updata:
                values.put("name","书名更新了！");
                resolver.update(uri,values,"name = ?",new String[]{"第一本书"});
                queryData();
                break;
            case R.id.bt_query:

                queryData();
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
}
