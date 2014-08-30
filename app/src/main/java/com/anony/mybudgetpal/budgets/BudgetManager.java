package com.anony.mybudgetpal.budgets;

import android.content.ContentValues;
import android.database.Cursor;

import com.anony.mybudgetpal.db.BudgetsContract.Budgets;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

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

    public List<Budget> getAllBudgets(){
        return _queryDatabaseForList(null, null);
    }

    public Budget addBudget(int dailyLimit, Date startDate, Date endDate) {
        Budget budget = new Budget(dailyLimit, startDate, endDate);
        int id = _add(budget);
        budget.setId(id);
        return budget;
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

    @Override
    protected ContentValues _getContentValues(Budget budget) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Budgets.COLUMN_NAME_DAILY_BUDGET, budget.getDailyLimit());
        contentValues.put(Budgets.COLUMN_NAME_START_DATE,   _formatDate(budget.getStartDate()));
        contentValues.put(Budgets.COLUMN_NAME_END_DATE,     _formatDate(budget.getEndDate()));
        return contentValues;
    }
}
