package com.anony.mybudgetpal.budgets;

import java.util.Date;

/**
 * Created by Natalie on 8/27/2014.
 */
public class Expense {
    private int m_id;
    private Budget m_budget;
    private Store m_store;
    private int m_cost;
    private Date m_date;

    public Expense(int expenseId, Budget budget, Store store, int cost, Date date){
        m_id        = expenseId;
        m_budget    = budget;
        m_store     = store;
        m_cost      = cost;
        m_date      = date;
    }

    public void setBudget(Budget budget){
        m_budget = budget;
    }

    public void setStore(Store store){
        m_store = store;
    }

    public int getId(){
        return m_id;
    }

    public Budget getBudget(){
        return m_budget;
    }

    public Store getStore(){
        return m_store;
    }

    public int getCost(){
        return m_cost;
    }

    public Date getDate(){
        return m_date;
    }
}
