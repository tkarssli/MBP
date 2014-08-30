package com.anony.mybudgetpal.budgets;

import android.content.ContentValues;
import android.database.Cursor;

import com.anony.mybudgetpal.db.BudgetsContract.Stores;

/**
 * Created by Natalie on 8/27/2014.
 */
public class StoreManager extends Manager<Store> {
    private static StoreManager s_instance = null;

    private StoreManager(){
        super(Stores.TABLE_NAME, new String[]{
            Stores.COLUMN_NAME_STORE_ID,
            Stores.COLUMN_NAME_NAME
        });
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
    protected ContentValues _getContentValues(Store store) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Stores.COLUMN_NAME_STORE_ID,  store.getId());
        contentValues.put(Stores.COLUMN_NAME_NAME,      store.getName());
        return contentValues;
    }
}
