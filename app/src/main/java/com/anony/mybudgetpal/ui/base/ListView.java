package com.anony.mybudgetpal.ui.base;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * TODO: document your custom view class.
 */
public class ListView extends android.widget.ListView {
    public ListView(Context context) {
        super(context);
    }

    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
