package com.sqlitesample1.database;

import android.content.Context;
import android.database.Cursor;

import com.sqlitesample1.EmployeeModel;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrunal3006 on 24-Jul-18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler singleton;

    private final String SQL_KEY="somePassword";

    public static DatabaseHandler getInstance(final Context context) {
        if (singleton == null) {
            singleton = new DatabaseHandler(context);
        }
        return singleton;
    }



    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EmployeeExample";

    private final Context context;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // Good idea to have the context that doesn't die with the window
        this.context = context.getApplicationContext();
        init(context);
    }


    private void init(Context context){
        SQLiteDatabase.loadLibs(context);
        getWritableDatabase(); // Trigger db creation
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Employee.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public synchronized Employee getPerson(final long id) {
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(Employee.TABLE_NAME, Employee.FIELDS,
                Employee.COL_ID + " IS ?", new String[] { String.valueOf(id) },
                null, null, null, null);
        if (cursor == null || cursor.isAfterLast()) {
            return null;
        }

        Employee item = null;
        if (cursor.moveToFirst()) {
            item = new Employee(cursor);
        }
        cursor.close();
        return item;
    }


    public synchronized List<EmployeeModel> getAllEmployee(){
        Employee item = null;
        List<EmployeeModel> employeeModelList  = new ArrayList<>();

        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(Employee.TABLE_NAME, Employee.FIELDS, null, null, null, null, null, null);
        if (cursor == null || cursor.isAfterLast()) {
            return employeeModelList;
        }



        if (cursor.moveToFirst()) {
            do {

                employeeModelList.add(new EmployeeModel(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return employeeModelList;
    }
    public synchronized boolean putPerson(final Employee person) {
        boolean success = false;
        int result = 0;
        final SQLiteDatabase db = this.getWritableDatabase();

        if (person.id > -1) {
            result += db.update(Employee.TABLE_NAME, person.getContent(),
                    Employee.COL_ID + " IS ?",
                    new String[] { String.valueOf(person.id) });
        }

        if (result > 0) {
            success = true;
        } else {
            // Update failed or wasn't possible, insert instead
            final long id = db.insert(Employee.TABLE_NAME, null,
                    person.getContent());

            if (id > -1) {
                person.id = id;
                success = true;
            }
        }

        if (success) {
            notifyProviderOnPersonChange();
        }

        return success;
    }

    public synchronized int removePerson(final Employee person) {
        final SQLiteDatabase db = this.getWritableDatabase();
        final int result = db.delete(Employee.TABLE_NAME,
                Employee.COL_ID + " IS ?",
                new String[] { Long.toString(person.id) });

        if (result > 0) {
            notifyProviderOnPersonChange();
        }
        return result;
    }

    private void notifyProviderOnPersonChange() {
        context.getContentResolver().notifyChange(
                EmployeeProvider.URI_EMPLOYEE, null, false);
    }
    public SQLiteDatabase getReadableDatabase(){
        return getReadableDatabase(SQL_KEY);
    }

    public SQLiteDatabase getWritableDatabase(){
        return getWritableDatabase(SQL_KEY);
    }
}
