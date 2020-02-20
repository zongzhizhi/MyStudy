package com.example.materiatest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class BookDataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "BookDataBaseHelper";

    public static final String TABLE_BOOK = "Book";

    public static final String TABLE_CATEGORY = "Category";

    /*
    * 图书表
    * 自增ID
    * 作者
    * 价格
    * 页数
    * 书名
    * */
    public static final String CREAT_BOOK = "create table "
            + TABLE_BOOK
            + "(id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";

    /*
    * 图书分类表
    * 类别名
    * 类别编码
    * */
    public static final String CREATE_CATEGORY = "create table "
            + TABLE_CATEGORY
            + "(id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";

    private Context mContext;

    public BookDataBaseHelper(@Nullable Context context,
                              @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Log.i(TAG, "onCreate: " + "数据库创建成功！");
        Toast.makeText(mContext,"数据库创建成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade: " + "数据库更新成功！");
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
