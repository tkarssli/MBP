package com.anony.mybudgetpal.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anony.mybudgetpal.R;
import com.anony.mybudgetpal.budgets.Budget;
import com.anony.mybudgetpal.util.Formatter;

/**
 * TODO: document your custom view class.
 */
public class BudgetListItemView extends RelativeLayout {
    private TextView m_currency;
    private TextView m_dailyLimit;
    private TextView m_startDate;
    private TextView m_endDate;

    public BudgetListItemView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BudgetListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BudgetListItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    public static BudgetListItemView inflate(ViewGroup parent){
        return (BudgetListItemView)LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_budget_list_item, parent, false);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
            attrs,
            R.styleable.BudgetListItemView,
            defStyle,
            0
        );

        LayoutInflater.from(context).inflate(R.layout.view_budget_list_item_children, this, true);
        _setUpChildren();
    }

    /**
     * Updates the information displayed using the given budget.
     *
     * @param budget The budget to update the views with.
     */
    public void setBudget(Budget budget){
        // TODO: Make currency symbol dynamic.
        m_currency.setText("$");
        m_dailyLimit.setText(String.valueOf(budget.getDailyLimit()));
        m_startDate.setText(Formatter.getInstance().format(budget.getStartDate()));
        m_endDate.setText(Formatter.getInstance().format(budget.getEndDate()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void _setUpChildren(){
        m_currency      = (TextView)findViewById(R.id.budgetListItem_currency);
        m_dailyLimit    = (TextView)findViewById(R.id.budgetListItem_dailyLimit);
        m_startDate     = (TextView)findViewById(R.id.budgetListItem_startDateView);
        m_endDate       = (TextView)findViewById(R.id.budgetListItem_endDateView);
    }
}
