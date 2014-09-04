package com.anony.mybudgetpal.ui.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.anony.mybudgetpal.R;
import com.anony.mybudgetpal.util.Formatter;

/**
 * TODO: document your custom view class.
 */
public abstract class SubHeaderView extends RelativeLayout {
    static final int COLLAPSED  = 0;
    static final int EXPANDED   = 1;

    private FrameLayout m_contentWrapper;
    private boolean m_isCollapsed;

    public SubHeaderView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public SubHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public SubHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        LayoutInflater.from(context).inflate(R.layout.view_sub_header_children, this, true);
        m_contentWrapper = (FrameLayout)findViewById(R.id.subHeader_contentContainer);

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.SubHeaderView, defStyle, 0);

        // Adjust the size of the header according to the requested initial state.
        m_isCollapsed = a.getInt(R.styleable.SubHeaderView_defaultState, COLLAPSED) == COLLAPSED;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width  = LayoutParams.MATCH_PARENT;
        layoutParams.height = Formatter.getInstance().dpToPx( m_isCollapsed ? 48 : 203 );
        setLayoutParams(layoutParams);

        a.recycle();
    }

    public boolean isCollapsed(){
        return m_isCollapsed;
    }

    protected FrameLayout getContentContainer(){
        return m_contentWrapper;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
