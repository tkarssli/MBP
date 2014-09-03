package com.anony.mybudgetpal.budgets;

import java.util.Date;

/**
 * Contains all the information for an expense.
 *
 * Created by Natalie on 8/27/2014.
 */
public class Expense {
    private int m_id = 0;
    private Budget m_budget = null;
    private Store m_store = null;
    private String m_name;
    private int m_cost;
    private Date m_date;

    /**
     * Internal constructor used for loading an expense from the database.
     *
     * @param expenseId The internal ID for this expense.
     * @param budget    The budget this expense belongs to.
     * @param store     The store where this expense happened.
     * @param name      The name of this expense.
     * @param cost      The cost, in pennies for this expense.
     * @param date      The date the expense was created.
     */
    protected Expense(int expenseId, Budget budget, Store store, String name, int cost, Date date){
        m_id        = expenseId;
        m_budget    = budget;
        m_store     = store;
        m_cost      = cost;
        m_date      = date;
    }

    /**
     * Constructor for creating new expenses.
     *
     * @param name The name of the new expense.
     * @param cost The cost of the expense, in pennies.
     * @param date The date the expense is for.
     */
    protected Expense(String name, int cost, Date date){
        m_name = name;
        m_cost = cost;
        m_date = date;
    }

    protected void setId(int id){
        m_id = id;
    }

    protected void setBudget(Budget budget){
        m_budget = budget;
    }

    protected void setStore(Store store){
        m_store = store;
    }

    public boolean isSaved(){
        return m_id != 0;
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

    public String getName(){
        return m_name;
    }

    public int getCost(){
        return m_cost;
    }

    public double getRealCost(){
        return ((double)m_cost) / 100.0;
    }

    public Date getDate(){
        return m_date;
    }
}
