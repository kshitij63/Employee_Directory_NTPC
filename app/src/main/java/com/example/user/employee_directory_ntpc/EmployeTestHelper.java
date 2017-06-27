package com.example.user.employee_directory_ntpc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 6/27/2017.
 */

public class EmployeTestHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="employe.db";
    private static final int DATABASE_VERSION=1;
    public EmployeTestHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table employee(_id integer primary key autoincrement,name text,grade text,department text,photo text,mobile_call text,mobile_sms text,email text,phone_r text,phone_o text,rax_o text,rax_r text,qtr_num text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
