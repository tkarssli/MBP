package com.anony.mybudgetpal.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anony.mybudgetpal.R;

public class Welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Hide Action Bar
        getActionBar().hide();

        // Setup Fonts
        TextView welcomeText = (TextView) findViewById(R.id.welcome_title);
        Button welcomeButton = (Button) findViewById(R.id.welcome_continueButton);
        Typeface robMed = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        welcomeText.setTypeface(robMed);
        welcomeButton.setTypeface(robMed);

        //Bold Text
        TextView para1 = (TextView) findViewById(R.id.welcome_paragraph1);

        Spannable spannable = (Spannable)para1.getText();
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        spannable.setSpan(boldSpan,66,69, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    //Do nothing on back press
    @Override
    public void onBackPressed(){

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

    public void onWelcomeContinueButtonClicked(View view) {
        Intent intent = new Intent(this, CreateBudget.class);
        startActivity(intent);
    }
}
