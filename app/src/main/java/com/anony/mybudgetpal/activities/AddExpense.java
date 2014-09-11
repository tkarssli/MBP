package com.anony.mybudgetpal.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.anony.mybudgetpal.R;
import com.anony.mybudgetpal.budgets.ExpenseManager;
import com.anony.mybudgetpal.ui.LabeledTextView;
import com.anony.mybudgetpal.ui.NavDrawerItem;
import com.anony.mybudgetpal.ui.adapter.NavDrawerListAdapter;
import com.anony.mybudgetpal.ui.base.ListView;

import java.util.ArrayList;
import java.util.Date;


public class AddExpense extends Activity {
    private ArrayList<NavDrawerItem>navDrawerItems;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private android.widget.ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.expense_drawer_layout);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList = (android.widget.ListView) findViewById(R.id.left_drawer);

        // TODO ListView adapter
        navDrawerItems = new ArrayList<NavDrawerItem>();
        navDrawerItems.add(new NavDrawerItem("Budget",this.getResources().getIdentifier("timer","drawable",this.getPackageName())));

        mDrawerList.setAdapter(new NavDrawerListAdapter(getApplicationContext(), navDrawerItems));
        // TODO List click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Setup Fonts
        TextView txt = (TextView) findViewById(R.id.title);
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");
        txt.setTypeface(font);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();

            }

        };




    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        // TODO add items to action bar and remove or show them here.
        //menu.findItem(PUTITEM HERE).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_expense, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    public void onSubmitButtonClicked(View view) {
        // Create a new expense.
        String name = ((LabeledTextView)findViewById(R.id.addExpense_nameInput)).getText();
        int amount = (int)(((LabeledTextView)findViewById(R.id.addExpense_priceInput)).getDouble() * 100.0);
        Date date = new Date();
        ExpenseManager.getInstance().createExpense(name, amount, date);

        // TODO Now would be a good time to change to the expense list page. :)
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id){
            selectItem(position);
        }
    }
    private void selectItem(int position){

    }
}
