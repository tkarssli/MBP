package com.anony.mybudgetpal.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.anony.mybudgetpal.R;

public class LoadingSplash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_splash);


        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Hide Action Bar
        getActionBar().hide();

        // Set Font
        TextView logo = (TextView)findViewById(R.id.loadingSplash_logo);

        //Set Logo word colors
        Spannable spannable = (Spannable)logo.getText();
        ForegroundColorSpan my_span = new ForegroundColorSpan(Color.parseColor("#7CB342"));
        ForegroundColorSpan budget_span = new ForegroundColorSpan(Color.parseColor("#558B2F"));
        ForegroundColorSpan pal_span = new ForegroundColorSpan(Color.parseColor("#33691E"));

        spannable.setSpan(my_span, 0,2,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(budget_span, 2,8,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(pal_span, 8,11,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.loading_splash, menu);
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

    public void onLogoClicked(View view) {
        // TODO: Check the database for an existing budget and go to the budget summary activity
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }
}
