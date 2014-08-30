package com.anony.mybudgetpal.budgets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anony.mybudgetpal.db.BudgetsContract.Budgets;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by Natalie on 8/27/2014.
 */
public class BudgetManager extends Manager<Budget> {
    private static BudgetManager s_instance = null;

    private BudgetManager(){
        super(Budgets.TABLE_NAME, new String[]{
                Budgets.COLUMN_NAME_BUDGET_ID,
                Budgets.COLUMN_NAME_DAILY_BUDGET,
                Budgets.COLUMN_NAME_START_DATE,
                Budgets.COLUMN_NAME_END_DATE
        });
    }

    public static BudgetManager getInstance(){
        if( s_instance == null ){
            s_instance = new BudgetManager();
        }
        return s_instance;
    }

    public Budget getBudget(int budgetId){
        return _get(budgetId);
    }

    public Budget getBudgetForDate(Date date) throws RuntimeException {
        String selection =
            "? BETWEEN " + Budgets.COLUMN_NAME_START_DATE +
            " AND COALESCE(" + Budgets.COLUMN_NAME_END_DATE + ", DATE('now'))";
        String[] selectionArgs = new String[]{
            _formatDate(date)
        };
        return _queryDatabaseForItem(selection, selectionArgs);
    }

    @Override
    protected Budget _loadFromDB(Cursor row) {
        try {
            return new Budget(
                row.getInt(row.getColumnIndexOrThrow(Budgets.COLUMN_NAME_BUDGET_ID)),
                row.getInt(row.getColumnIndexOrThrow(Budgets.COLUMN_NAME_DAILY_BUDGET)),
                _parseDateString(row.getString(row.getColumnIndexOrThrow(Budgets.COLUMN_NAME_START_DATE))),
                _parseDateString(row.getString(row.getColumnIndexOrThrow(Budgets.COLUMN_NAME_END_DATE)))
            );
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
