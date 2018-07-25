package com.sqlitesample1.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by mrunal3006 on 24-Jul-18.
 */

public class EmployeeProvider extends ContentProvider {


    public static final String AUTHORITY = "com.sqlitesample1.provider";
    public static final String SCHEME = "content://";


    public static final String EMPLOYEE = SCHEME + AUTHORITY + "/employee";
    public static final Uri URI_EMPLOYEE = Uri.parse(EMPLOYEE);
    public static final String EMPLOYEE_BASE = EMPLOYEE + "/";

    public EmployeeProvider() {
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor result = null;
        Log.d(TAG, "URI:"+uri);
        if (URI_EMPLOYEE.equals(uri)) {

            result = DatabaseHandler
                    .getInstance(getContext())
                    .getReadableDatabase()
                    .query(Employee.TABLE_NAME, Employee.FIELDS, null, null, null,
                            null, null, null);
            result.setNotificationUri(getContext().getContentResolver(), URI_EMPLOYEE);
        } else if (uri.toString().startsWith(EMPLOYEE_BASE)) {
            final long id = Long.parseLong(uri.getLastPathSegment());
            result = DatabaseHandler
                    .getInstance(getContext())
                    .getReadableDatabase()
                    .query(Employee.TABLE_NAME, Employee.FIELDS,
                            Employee.COL_ID + " IS ?",
                            new String[] { String.valueOf(id) }, null, null,
                            null, null);
            result.setNotificationUri(getContext().getContentResolver(), URI_EMPLOYEE);
        } else {
            throw new UnsupportedOperationException("Not yet implemented");
        }

        return result;

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
