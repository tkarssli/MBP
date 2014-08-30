package com.anony.mybudgetpal.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.anony.mybudgetpal.Application;

/**
 * Created by Natalie on 8/27/2014.
 */
public class BudgetsDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION   = 1;
    private static final String FILE_NAME       = "budgets.db";

    public BudgetsDatabase(Context context) {
        super(context, FILE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BudgetsContract.MAKE_BUDGETS_TABLE);
        db.execSQL(BudgetsContract.MAKE_STORES_TABLE);
        db.execSQL(BudgetsContract.MAKE_EXPENSES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Nothing to upgrade yet, we're on version 1.
    }
}
