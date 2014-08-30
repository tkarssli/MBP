package com.anony.mybudgetpal.budgets;

import android.content.ContentValues;
import android.database.Cursor;

import com.anony.mybudgetpal.db.BudgetsContract.Expenses;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Natalie on 8/27/2014.
 */
public class ExpenseManager extends Manager<Expense> {
    private static ExpenseManager s_instance = null;

    private ExpenseManager(){
        super(Expenses.TABLE_NAME, new String[]{
            Expenses.COLUMN_NAME_EXPENSE_ID,
            Expenses.COLUMN_NAME_BUDGET_ID,
            Expenses.COLUMN_NAME_STORE_ID,
            Expenses.COLUMN_NAME_PURCHASE_DATE,
            Expenses.COLUMN_NAME_COST
        });
    }

    public static ExpenseManager getInstance(){
        if( s_instance == null ){
            s_instance = new ExpenseManager();
        }
        return s_instance;
    }

    public List<Expense> getExpenses(Budget budget){
        // Assemble our query.
        String selection = Expenses.COLUMN_NAME_PURCHASE_DATE + " BETWEEN ? AND COALESCE(?, DATE('now'))";
        String[] selectionArgs = new String[]{
            _formatDate(budget.getStartDate()),
            budget.getEndDate() == null ? null : _formatDate(budget.getEndDate())
        };
        return _queryDatabaseForList(selection, selectionArgs);
    }

    public void addExpense(Expense expense){
        _add(expense);
    }

    @Override
    protected Expense _loadFromDB(Cursor row) {
        try {
            return new Expense(
                    row.getInt(row.getColumnIndexOrThrow(Expenses.COLUMN_NAME_EXPENSE_ID)),
                    BudgetManager.getInstance().getBudget(row.getInt(row.getColumnIndexOrThrow(Expenses.COLUMN_NAME_BUDGET_ID))),
                    StoreManager.getInstance().getStore(row.getInt(row.getColumnIndexOrThrow(Expenses.COLUMN_NAME_STORE_ID))),
                    row.getInt(row.getColumnIndexOrThrow(Expenses.COLUMN_NAME_COST)),
                    _parseDateString(row.getString(row.getColumnIndexOrThrow(Expenses.COLUMN_NAME_PURCHASE_DATE)))
            );
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected ContentValues _getContentValues(Expense expense) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Expenses.COLUMN_NAME_BUDGET_ID,       expense.getBudget().getId());
        contentValues.put(Expenses.COLUMN_NAME_STORE_ID,        expense.getStore().getId());
        contentValues.put(Expenses.COLUMN_NAME_PURCHASE_DATE,   _formatDate(expense.getDate()));
        contentValues.put(Expenses.COLUMN_NAME_COST,            expense.getCost());
        return contentValues;
    }
}
