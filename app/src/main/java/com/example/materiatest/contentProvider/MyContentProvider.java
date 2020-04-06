package com.example.materiatest.contentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MergeCursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.util.Log;

import com.example.materiatest.db.BookDataBaseHelper;


/*
*A分支第一次创建
* A分支在B分支提交后修改了一笔
* A分支第二次commit   B分支修改了A分支代码并提交到Master分支
* B分支第一次创建
*
*
* A分支添加AAAAA
* */
public class MyContentProvider extends ContentProvider {

    private static final String CONTENT = "content://";

    public static final String TAB_BOOK = "Book";
    public static final String TAB_CATEGORY = "Category";

    public static final String AUTHORITIES = "com.example.materiatest.provider";

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    private static UriMatcher matcher;
    private BookDataBaseHelper helper;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITIES, "book", BOOK_DIR);
        matcher.addURI(AUTHORITIES, "book/#", BOOK_ITEM);
        matcher.addURI(AUTHORITIES, "category", CATEGORY_DIR);
        matcher.addURI(AUTHORITIES, "category/#", CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        helper = new BookDataBaseHelper(getContext(), "BookStore.db", null, 2);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase database = helper.getWritableDatabase();
        switch (matcher.match(uri)) {
            case BOOK_DIR:
                Log.i(TAG, "query: " + BOOK_DIR);
                cursor = database.query(TAB_BOOK, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case BOOK_ITEM:
                /*
                 * uri.getPathSegments()将URI权限之后的部分以./符号进行分割，分割后的结果放在字符串列表中，
                 * 列表0的位置存放路径，第一个位置存放的就是id
                 * */
                String bookId = uri.getPathSegments().get(1);
                cursor = database.query(TAB_BOOK, projection, "id = ?", new String[]{bookId},
                        null, null, sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = database.query(TAB_CATEGORY, projection, selection, selectionArgs, null,
                        null, sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = database.query(TAB_CATEGORY, projection, "id = ?",
                        new String[]{categoryId}, null, null, sortOrder);
                break;
        }

        if(cursor != null){
            Log.i(TAG, "query: setNotificationUri" + "---->" + uri.toString());
            cursor.setNotificationUri(getContext().getContentResolver(),uri);
        }
        return cursor;
    }

    private static final String TAG = "123";
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = helper.getWritableDatabase();
        Uri returnUri = null;
        switch (matcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = database.insert(TAB_BOOK, null, values);
                returnUri = Uri.parse(CONTENT + AUTHORITIES + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategory = database.insert(TAB_CATEGORY, null, values);
                returnUri = Uri.parse(CONTENT + AUTHORITIES + "/category/" + newCategory);
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return returnUri;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = helper.getWritableDatabase();
        int updataRows = 0;
        switch (matcher.match(uri)) {
            case BOOK_DIR:
                updataRows = database.update(TAB_BOOK, values, selection, selectionArgs);
                Log.i(TAG, "update: "+"---uri --->" + uri.toString());
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updataRows = database.update(TAB_BOOK, values, "id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updataRows = database.update(TAB_CATEGORY, values, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updataRows = database.update(TAB_CATEGORY, values, "id = ?",
                        new String[]{categoryId});
                break;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return updataRows;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = helper.getWritableDatabase();
        int deleteRows = 0;
        switch (matcher.match(uri)) {
            case BOOK_DIR:
                deleteRows = database.delete(TAB_BOOK, selection, selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = database.delete(TAB_BOOK, "id = ?", new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = database.delete(TAB_CATEGORY, selection, selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = database.delete(TAB_CATEGORY, "id = ?",
                        new String[]{categoryId});
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.materiatest.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.materiatest.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.materiatest.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.materiatest.provider.category";
        }
        return null;
    }
}
