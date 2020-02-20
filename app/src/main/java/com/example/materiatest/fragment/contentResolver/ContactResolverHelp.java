package com.example.materiatest.fragment.contentResolver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.materiatest.fragment.ContentResolverFragment;

import java.util.ArrayList;
import java.util.List;

public class ContactResolverHelp {

    private static final String TAG = "ContactResolverHelp";

    private ContentResolverFragment resolverFragment;
    private List<String> strings;
    private ContentResolverData resolverData;

    public ContactResolverHelp(ContentResolverFragment resolverFragment) {
        this.resolverFragment = resolverFragment;
        this.strings = new ArrayList<>();
        this.resolverData = resolverFragment;
    }


    /*
     * 获取手机联系人
     * */
    public void readContacts() {
        if (ContextCompat.checkSelfPermission(resolverFragment.getContext(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(resolverFragment.getActivity(), new String[]{
                    Manifest.permission.READ_CONTACTS}, 1);
        } else {
            Cursor cursor = null;
            cursor =
                    resolverFragment.getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                strings.clear();
                while (cursor.moveToNext()) {
                    String name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String number =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    strings.add(name + "电话为：" + number);
                }
                cursor.close();
            }
            if (resolverData != null && cursor != null) {
                resolverData.setContentResolverData(strings);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    Toast.makeText(resolverFragment.getContext(), "没有权限！", Toast.LENGTH_LONG).show();
                    Log.i(TAG, "onRequestPermissionsResult: 没有权限");
                }
                break;
        }
    }
}
