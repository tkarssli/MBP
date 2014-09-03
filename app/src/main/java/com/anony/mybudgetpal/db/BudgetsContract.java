package com.anony.mybudgetpal.db;

import android.provider.BaseColumns;

/**
 * Created by Natalie on 8/27/2014.
 */
public final class BudgetsContract {
    public static abstract class Budgets implements BaseColumns {
        public static final String TABLE_NAME               = "budgets";
        public static final String COLUMN_NAME_BUDGET_ID    = Budgets._ID;
        public static final String COLUMN_NAME_START_DATE   = "startDate";
        public static final String COLUMN_NAME_END_DATE     = "endDate";
        public static final String COLUMN_NAME_DAILY_BUDGET = "dailyBudget";
    }

    public static abstract class Stores implements BaseColumns {
        public static final String TABLE_NAME           = "stores";
        public static final String COLUMN_NAME_STORE_ID = Stores._ID;
        public static final String COLUMN_NAME_NAME     = "storeName";
    }

    public static abstract class Expenses implements BaseColumns {
        public static final String TABLE_NAME                   = "expenses";
        public static final String COLUMN_NAME_EXPENSE_ID       = Expenses._ID;
        public static final String COLUMN_NAME_BUDGET_ID        = "budgetId";
        public static final String COLUMN_NAME_STORE_ID         = "storeId";
        public static final String COLUMN_NAME_NAME             = "expenseName";
        public static final String COLUMN_NAME_PURCHASE_DATE    = "purchaseDate";
        public static final String COLUMN_NAME_COST             = "cost";
    }

    private static final String _CREATE_TABLE   = "CREATE TABLE ";
    private static final String _DROP_TABLE     = "DROP TABLE ";
    private static final String _PRIMARY_KEY    = " INTEGER PRIMARY KEY ";
    private static final String _TEXT           = " TEXT ";
    private static final String _INT            = " INTEGER ";
    private static final String _FOREIGN_ID     = _INT + " REFERENCES ";
    private static final String _CURRENCY       = _INT;
    private static final String _DATE_TIME      = _INT;

    protected static final String MAKE_BUDGETS_TABLE =
        _CREATE_TABLE + Budgets.TABLE_NAME + " ( " +
            Budgets.COLUMN_NAME_BUDGET_ID       + _PRIMARY_KEY  + ", " +
            Budgets.COLUMN_NAME_START_DATE      + _DATE_TIME    + ", " +
            Budgets.COLUMN_NAME_END_DATE        + _DATE_TIME    + ", " +
            Budgets.COLUMN_NAME_DAILY_BUDGET    + _CURRENCY     +
        ");";

    protected static final String MAKE_STORES_TABLE =
        _CREATE_TABLE + Stores.TABLE_NAME + " ( " +
            Stores.COLUMN_NAME_STORE_ID + _PRIMARY_KEY  + ", " +
            Stores.COLUMN_NAME_NAME     + _TEXT         +
        ");";

    protected static final String MAKE_EXPENSES_TABLE =
        _CREATE_TABLE + Expenses.TABLE_NAME + " ( " +
            Expenses.COLUMN_NAME_EXPENSE_ID     + _PRIMARY_KEY  + ", " +
            Expenses.COLUMN_NAME_BUDGET_ID      + _FOREIGN_ID   + Budgets.TABLE_NAME    + "(" + Budgets.COLUMN_NAME_BUDGET_ID   + "), " +
            Expenses.COLUMN_NAME_STORE_ID       + _FOREIGN_ID   + Stores.TABLE_NAME     + "(" + Stores.COLUMN_NAME_STORE_ID     + "), " +
            Expenses.COLUMN_NAME_NAME           + _TEXT         + ", " +
            Expenses.COLUMN_NAME_PURCHASE_DATE  + _DATE_TIME    + ", " +
            Expenses.COLUMN_NAME_COST           + _CURRENCY     +
        ");";

    static final String DESTROY_BUDGETS_TABLE   = _DROP_TABLE + Budgets.TABLE_NAME;
    static final String DESTROY_STORES_TABLE    = _DROP_TABLE + Stores.TABLE_NAME;
    static final String DESTROY_EXPENSES_TABLE  = _DROP_TABLE + Expenses.TABLE_NAME;
}
