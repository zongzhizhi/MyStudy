package com.example.materiatest.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.materiatest.R;
import com.example.materiatest.db.BookDataBaseHelper;

import static com.example.materiatest.db.BookDataBaseHelper.TABLE_BOOK;

/*
* 这个fragment主要学习数据创建和升级以及数据库的增删改查功操作
*   1.创建数据库有两种方式：
*       1.1   主要通过继承SQLiteOpenHelper类来时先数据库创建升级和操作，举例代码看baseHelper相关操作
*       1.2   主要使用LitePal数据库框架  具体代码请看litePalHelp对象操作(注意这个方式主要通过映射的方式，
*             具体看 src/main/assets路径下litepal配置)
*
*
*
* */

public class DatabaseFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "DatabaseFragment";
    private TextView tv_creat;
    private TextView tv_updata;

    private TextView tv_add;
    private TextView tv_delete;
    private TextView tv_change;
    private TextView tv_retrieve;
    private TextView tv_data;

    private BookDataBaseHelper baseHelper;
    private SQLiteDatabase db;


    private LitePalHelp litePalHelp;
    @Override
    protected void init(View view) {
        tv_creat = view.findViewById(R.id.tv_creat);
        tv_updata = view.findViewById(R.id.tv_updata);
        tv_add = view.findViewById(R.id.tv_add);
        tv_delete = view.findViewById(R.id.tv_delete);
        tv_change = view.findViewById(R.id.tv_change);
        tv_retrieve = view.findViewById(R.id.tv_retrieve);
        tv_data = view.findViewById(R.id.tv_data);

        tv_add.setOnClickListener(this);
        tv_updata.setOnClickListener(this);
        tv_creat.setOnClickListener(this);
        tv_delete.setOnClickListener(this);
        tv_change.setOnClickListener(this);
        tv_retrieve.setOnClickListener(this);

//        baseHelper = new BookDataBaseHelper(getContext(), "book.db", null, 2);
//        db = baseHelper.getWritableDatabase();
        litePalHelp = new LitePalHelp();
        litePalHelp.setTextView(tv_data);
    }

    @Override
    protected int getFragmentView() {
        return R.layout.fragment_database;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_creat:

                break;
            case R.id.tv_updata:
//                baseHelper = new BookDataBaseHelper(getContext(), "book.db", null, 2);
                break;
            case R.id.tv_add:
//                AddDataVale();
                litePalHelp.addData();
                break;
            case R.id.tv_delete:
//                deleteData();
                litePalHelp.deleteData();
                break;
            case R.id.tv_change:
//                updata();
                litePalHelp.change();
                break;
            case R.id.tv_retrieve:
//                query();
                litePalHelp.retrieve();
                break;
        }
    }

    /*
     * 添加数据
     * */
    private void AddDataVale() {
        ContentValues values = new ContentValues();
        values.put("name", "第一行代码");
        values.put("pages", 543);
        values.put("price", 23.5);
        values.put("author", "郭霖");
        db.insert("Book", null, values);
        values.clear();

        values.put("name", "第二行代码");
        values.put("pages", 435);
        values.put("price", 43.8);
        values.put("author", "郭霖二号");
        db.insert("book", null, values);
        values.clear();



        query();
        Log.i(TAG, "AddDataVale: " + "数据插入成功！");
    }

    /*
     * 删除数据
     * */
    private void deleteData() {
        if (db == null) return;
        String whereClause = "name = ? or author = ?";
        String[] whereArgs = new String[]{"第一行代码", "郭霖二号"};
        db.delete(TABLE_BOOK, whereClause, whereArgs);
        Log.i(TAG, "deleteData: 删除成功！");
        query();
    }

    /*
     * 修改数据
     * */
    private void updata() {
        if (db == null) return;
        ContentValues values = new ContentValues();
        values.put("name", "第二行代码");
        /*
         *将TABLE_BOOK表中price = 43.8的行name字段修改为"第二行代码"
         * */
        String whereClause = "price = ?";
        String[] whereArgs = new String[]{"43.8"};
        db.update(TABLE_BOOK, values, whereClause, whereArgs);
        values.clear();
        Log.i(TAG, "updata: 更新成功！");
        query();
    }

    /*
     * 查询数据库中所有数据
     * */
    private String query() {

        if (db == null) return "数据库未建立";

        StringBuilder builder = new StringBuilder();

        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                builder.append("name = ")
                        .append(name)
                        .append(" author = ")
                        .append(author)
                        .append(" pages = ")
                        .append(pages)
                        .append(" price = ")
                        .append(price)
                        .append("\n");
                Log.i(TAG, "query: " + builder.toString());
            } while (cursor.moveToNext());
        }

        tv_data.setText(builder.toString());
        return builder.toString();
    }
}
