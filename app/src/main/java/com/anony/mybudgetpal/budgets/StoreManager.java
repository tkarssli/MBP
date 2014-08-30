package com.anony.mybudgetpal.budgets;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anony.mybudgetpal.db.BudgetsContract.Stores;

import java.text.ParseException;

/**
 * Created by Natalie on 8/27/2014.
 */
public class StoreManager extends Manager<Store> {
    private static StoreManager s_instance = null;

    private StoreManager(){
        super(Stores.COLUMN_NAME_STORE_ID);
    }

    public static StoreManager getInstance(){
        if( s_instance == null ){
            s_instance = new StoreManager();
        }
        return s_instance;
    }

    public Store getStore(int storeId){
        return _get(storeId);
    }

    @Override
    protected Store _loadFromDB(Cursor row) {
        return new Store(
            row.getInt(row.getColumnIndexOrThrow(Stores.COLUMN_NAME_STORE_ID)),
            row.getString(row.getColumnIndexOrThrow(Stores.COLUMN_NAME_NAME))
        );
    }

    @Override
    protected Cursor _performDatabaseQuery(SQLiteDatabase db, String selection, String[] selectionArgs) {
        // Assemble a list of columns to fetch.
        String[] columns = new String[]{
            Stores.COLUMN_NAME_STORE_ID,
            Stores.COLUMN_NAME_NAME
        };

        // Perform the query.
        return db.query(
                Stores.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
    }
}
