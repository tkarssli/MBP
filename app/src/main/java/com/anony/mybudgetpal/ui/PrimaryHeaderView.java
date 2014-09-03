package com.anony.mybudgetpal.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anony.mybudgetpal.R;

/**
 * TODO: document your custom view class.
 */
public class PrimaryHeaderView extends RelativeLayout {

    public PrimaryHeaderView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public PrimaryHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PrimaryHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Get the views we care about from the layout.
        LayoutInflater.from(context).inflate(R.layout.view_primary_header_children, this, true);
        TextView title          = (TextView)findViewById(R.id.primaryHeader_title);
        ImageButton hamburger   = (ImageButton)findViewById(R.id.primaryHeader_hamburgerButton);
        ImageButton settings    = (ImageButton)findViewById(R.id.primaryHeader_settingsButton);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.PrimaryHeaderView, defStyle, 0);

        title.setText(a.getText(R.styleable.PrimaryHeaderView_title));
        hamburger.setVisibility(a.getBoolean(R.styleable.PrimaryHeaderView_showHamburger, true) ? View.VISIBLE : View.INVISIBLE);
        settings.setVisibility(a.getBoolean(R.styleable.PrimaryHeaderView_showSettings, true) ? View.VISIBLE : View.INVISIBLE);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
