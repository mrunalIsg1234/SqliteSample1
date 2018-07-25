package com.sqlitesample1.database;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by mrunal3006 on 24-Jul-18.
 */

public class Employee {

    public static final String TABLE_NAME = "employee";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_DESIGNATION = "designation";
    public static final String COL_AGE = "age";
    public static final String COL_JOINING_DATE = "joinigdate";

    public static final String[] FIELDS = { COL_ID, COL_NAME, COL_DESIGNATION, COL_AGE,COL_JOINING_DATE };

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COL_ID + " INTEGER PRIMARY KEY,"
                    + COL_NAME + " TEXT NOT NULL DEFAULT '',"
                    + COL_DESIGNATION + " TEXT NOT NULL DEFAULT '',"
                    + COL_AGE + " TEXT NOT NULL DEFAULT '',"
                    + COL_JOINING_DATE + " TEXT NOT NULL DEFAULT ''"
                    + ")";

    // Fields corresponding to database columns
    public long id = -1;
    public String name = "";
    public String designation = "";
    public String age = "";
    public String joiningDate="";

    public Employee() {
    }

    public Employee(final Cursor cursor) {
        // Indices expected to match order in FIELDS!
        this.id = cursor.getLong(0);
        this.name = cursor.getString(1);
        this.designation = cursor.getString(2);
        this.age = cursor.getString(3);
        this.joiningDate = cursor.getString(4);
    }

    public ContentValues getContent() {
        final ContentValues values = new ContentValues();
        // Note that ID is NOT included here
        values.put(COL_NAME, name);
        values.put(COL_DESIGNATION, designation);
        values.put(COL_AGE, age);
        values.put(COL_JOINING_DATE, joiningDate);

        return values;
    }
}
