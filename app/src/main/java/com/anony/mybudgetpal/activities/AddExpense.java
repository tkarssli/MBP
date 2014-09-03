package com.anony.mybudgetpal.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.anony.mybudgetpal.R;
import com.anony.mybudgetpal.budgets.ExpenseManager;
import com.anony.mybudgetpal.ui.LabeledTextView;

import java.util.Date;

public class AddExpense extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_expense, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
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
}
