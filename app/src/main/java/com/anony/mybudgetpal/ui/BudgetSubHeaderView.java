package com.anony.mybudgetpal.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.anony.mybudgetpal.R;
import com.anony.mybudgetpal.budgets.Budget;
import com.anony.mybudgetpal.ui.base.SubHeaderView;

import java.util.Date;

/**
 * TODO: document your custom view class.
 */
public class BudgetSubHeaderView extends SubHeaderView {
    private RelativeLayout m_dailyLimitContainer;
    private TextView m_remainingText;
    private TextView m_remainingBudget;
    private TextView m_dailyBudgetLimit;
    private Budget m_budget;
    private Date m_date = new Date();

    public BudgetSubHeaderView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BudgetSubHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BudgetSubHeaderView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        LayoutInflater.from(context).inflate(R.layout.view_budget_sub_header_children, getContentContainer(), false);
        m_dailyLimitContainer   = (RelativeLayout)findViewById(R.id.budgetSubHeader_dailyLimitContainer);
        m_remainingText         = (TextView)m_dailyLimitContainer.findViewById(R.id.budgetSubHeader_remaining);
        m_remainingBudget       = (TextView)findViewById(R.id.budgetSubHeader_remaining);
        m_dailyBudgetLimit      = (TextView)findViewById(R.id.budgetSubHeader_dailyLimit);

        _updatePositioning();
    }

    private void _updatePositioning(){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)m_dailyLimitContainer.getLayoutParams();

        // In a collapsed state we align the daily limit container to the end of the remainder
        // container. In the expanded case, the daily limit container is below the remainder.
        if (isCollapsed()) {
            layoutParams.removeRule(RelativeLayout.BELOW);
            layoutParams.addRule(RelativeLayout.END_OF,     R.id.budgetSubHeader_remainderContainer);
            layoutParams.addRule(RelativeLayout.RIGHT_OF,   R.id.budgetSubHeader_remainderContainer);
            m_remainingText.setVisibility(View.GONE);
        }
        else {
            layoutParams.removeRule(RelativeLayout.END_OF);
            layoutParams.removeRule(RelativeLayout.RIGHT_OF);
            layoutParams.addRule(RelativeLayout.BELOW, R.id.budgetSubHeader_remainderContainer);
            m_remainingText.setVisibility(View.VISIBLE);
        }

        m_dailyLimitContainer.setLayoutParams(layoutParams);
    }

    public void setBudget(Budget budget){
        m_budget = budget;
        updateBudget();
    }

    public void updateBudget(){
        if (m_budget == null){
            return;
        }

        m_remainingBudget.setText(String.valueOf(m_budget.getRealRemainingBudget(m_date)));
        m_dailyBudgetLimit.setText(String.valueOf((int)m_budget.getRealDailyLimit()));
    }

    public void setDate(Date date){
        m_date = date;
        updateBudget();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
