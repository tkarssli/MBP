package com.anony.mybudgetpal.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.anony.mybudgetpal.budgets.Budget;
import com.anony.mybudgetpal.ui.BudgetListItemView;

import java.util.List;

/**
 * Created by Natalie on 9/1/2014.
 */
public class BudgetAdapter extends ArrayAdapter<Budget> {
    public BudgetAdapter(Context context){
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        BudgetListItemView view = (BudgetListItemView)convertView;
        if (view == null) {
            view = BudgetListItemView.inflate(parent);
        }
        view.setBudget(getItem(position));
        return view;
    }
}
