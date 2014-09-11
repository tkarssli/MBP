package com.anony.mybudgetpal.activities;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.anony.mybudgetpal.R;
import com.anony.mybudgetpal.budgets.Budget;
import com.anony.mybudgetpal.budgets.BudgetManager;
import com.anony.mybudgetpal.ui.adapter.BudgetAdapter;

import java.util.Calendar;
import java.util.Date;

public class CreateBudget extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_budget);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Hide Action Bar
        getActionBar().hide();

        // Set up fonts
        TextView para1 = (TextView) findViewById(R.id.create_budget_para1);
        Typeface robMed = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        para1.setTypeface(robMed);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_budget, menu);
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

    public void onCreateBudgetClicked(View view){
        // Calculate the budget's daily limit.
        int days = Integer.valueOf(((EditText)findViewById(R.id.durationCount)).getText().toString()) * 7;
        int dailyLimit = (int)(Double.valueOf(((EditText)findViewById(R.id.amount)).getText().toString()) * 100.0 / (double)days);

        // Calculate the start and end date of the budget.
        Date startDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, days);
        Date endDate = cal.getTime();

        // Create the budget and add it to our list.
        Budget budget = BudgetManager.getInstance().addBudget(dailyLimit, startDate, endDate);
    }
}
