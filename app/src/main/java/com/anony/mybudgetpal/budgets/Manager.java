package com.anony.mybudgetpal.budgets;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anony.mybudgetpal.Application;
import com.anony.mybudgetpal.util.Formatter;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Natalie on 8/29/2014.
 */
abstract class Manager<Type> {
    private Map<Integer, WeakReference<Type>> m_cache = new TreeMap<Integer, WeakReference<Type>>();
    private String m_tableName;
    private String[] m_columnNames;

    protected Manager(String tableName, String[] columnNames){
        m_tableName = tableName;
        m_columnNames = columnNames;
    }

    /**
     * Parses a date string into a Date object.
     *
     * @throws java.text.ParseException If the provided date string is invalid.
     *
     * @param dateString The string to parse.
     *
     * @return The parsed date.
     */
    protected Date _parseDateString(String dateString) throws ParseException {
        return Formatter.getInstance().parseDate(dateString);
    }

    /**
     * Formats a Date object into a string for storage in the database.
     *
     * @param date The Date object to format.
     *
     * @return The formatted date string.
     */
    protected String _formatDate(Date date){
        return Formatter.getInstance().format(date);
    }

    /**
     * Fetches an item with the given Id, first checking the in-memory cache, then the database.
     *
     * @param id The Id of the item to load.
     *
     * @return An object with the given Id if one exists, otherwise null.
     */
    protected Type _get(int id){
        Type obj = _getFromCache(id);
        if (obj == null){
            String selection = m_columnNames[0] + " = ?";
            String[] selectionArgs = new String[]{
                    String.valueOf(id)
            };
            obj = _queryDatabaseForItem(selection, selectionArgs);
        }
        return obj;
    }

    /**
     * Adds a new item to the database and in-memory cache.
     *
     * @param obj The object to insert into the database.
     *
     * @return The new Id for the item.
     */
    protected int _add(Type obj){
        // Get the content that will be inserted.
        ContentValues contentValues = _getContentValues(obj);

        // Insert the item into the database.
        SQLiteDatabase db = Application.getInstance().getBudgetsDB().getWritableDatabase();
        int id = (int)db.insert(m_tableName, null, contentValues);

        // Add the item to our cache too.
        m_cache.put(id, new WeakReference<Type>(obj));

        // Return the new Id of the item.
        return id;
    }

    /**
     * Checks the cache for an instance of Type with the given Id.
     *
     * @param id The Id of the cached item.
     *
     * @return The cached item, if found. Null if there is no item cached with this Id.
     */
    protected Type _getFromCache(int id){
        return m_cache.get(id).get();
    }

    /**
     * Pulls an item from the database matching the given selection.
     *
     * @param selection     The WHERE statement for the query.
     * @param selectionArgs Any substitution values for the WHERE clause.
     *
     * @return The item found for the given query if one is found. Null if not.
     */
    protected Type _queryDatabaseForItem(String selection, String[] selectionArgs){
        // Pull out the info from the DB.
        Cursor res = _performDatabaseQuery(selection, selectionArgs);
        if (res.getCount() == 0){
            throw new RuntimeException("Could not find the desired item.");
        }

        // Check for the item in the cache.
        res.moveToFirst();
        int id = res.getInt(res.getColumnIndexOrThrow(m_columnNames[0]));
        Type obj = _getFromCache(id);

        // If we don't have a cached instance, load it from the results we got.
        if (obj == null){
            obj = _loadFromDB(res);
            m_cache.put(id, new WeakReference<Type>(obj));
        }

        // Return the object.
        return obj;
    }

    /**
     * Queries the database for a list of results.
     *
     * @param selection     The WHERE clause for the query.
     * @param selectionArgs Any substitution values for the WHERE clause.
     *
     * @return The list of items matching the given query.
     */
    protected List<Type> _queryDatabaseForList(String selection, String[] selectionArgs){
        // Pull out the info from the DB.
        Cursor res = _performDatabaseQuery(selection, selectionArgs);
        if (res.getCount() == 0){
            throw new RuntimeException("Could not find the desired item.");
        }

        // Iterate over the list, extracting each item and caching any we don't already have cached.
        res.moveToFirst();
        List<Type> list = new LinkedList<Type>();
        do {
            int id = res.getInt(res.getColumnIndexOrThrow(m_columnNames[0]));
            Type obj = _getFromCache(id);
            if (obj == null){
                obj = _loadFromDB(res);
                m_cache.put(id, new WeakReference<Type>(obj));
            }
            list.add(obj);
        } while (res.moveToNext());

        return list;
    }

    /**
     * Performs a query against the database.
     *
     * @param selection     The WHERE clause for the query.
     * @param selectionArgs Any substitution values for the WHERE clause.
     *
     * @return The results of the query.
     */
    private Cursor _performDatabaseQuery(String selection, String[] selectionArgs){
        SQLiteDatabase db = Application.getInstance().getBudgetsDB().getReadableDatabase();
        return db.query(m_tableName, m_columnNames, selection, selectionArgs, null, null, null);
    }

    /**
     * Loads a single item from the given data set.
     *
     * @param row A single row of results from a DB query.
     *
     * @return An instance of Type loaded from the given data.
     */
    protected abstract Type _loadFromDB(Cursor row);

    protected abstract ContentValues _getContentValues(Type obj);
}
