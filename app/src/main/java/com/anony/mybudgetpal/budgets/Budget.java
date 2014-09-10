package com.anony.mybudgetpal.budgets;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Natalie on 8/27/2014.
 */
public class Budget {
    private int m_id;
    private int m_dailyLimit;
    private Date m_start;
    private Date m_end;
    private List<Expense> m_expenses = null;

    /**
     * Constructor for loading budgets from the database.
     *
     * @param id            The Id of the budget.
     * @param dailyLimit    The daily limit of the budget.
     * @param start         The starting date of the budget.
     * @param end           The end date for the budget.
     */
    protected Budget( int id, int dailyLimit, Date start, Date end ){
        m_id            = id;
        m_dailyLimit    = dailyLimit;
        m_start         = start;
        m_end           = end;
    }

    /**
     * Constructor for creating new budgets.
     *
     * @param dailyLimit    The daily limit for spending.
     * @param startDate     The date this budget starts.
     * @param endDate       The date the budget ends.
     */
    protected Budget(int dailyLimit, Date startDate, Date endDate) {
        m_id            = 0;
        m_dailyLimit    = dailyLimit;
        m_start         = startDate;
        m_end           = endDate;
    }

    /**
     * Sets the Id for this budget. Only used when creating a new budget.
     *
     * @param id The Id of the budget.
     */
    protected void setId(int id){
        m_id = id;
    }

    public void addExpense(Expense expense){
        expense.setBudget(this);
        m_expenses.add(expense);
    }

    public int getId(){
        return m_id;
    }

    /**
     * Fetches the daily limit for the budget.
     *
     * @return The daily spending limit, in pennies.
     */
    public int getDailyLimit(){
        return m_dailyLimit;
    }

    /**
     * Fetches the daily limit for the budget in dollars.
     *
     * @return The daily spending limit, in dollars.
     */
    public double getRealDailyLimit(){
        return ((double)getDailyLimit()) / 100.0;
    }

    public Date getStartDate(){
        return m_start;
    }

    public Date getEndDate(){
        return m_end;
    }

    public List< Expense > getExpenses(){
        if( m_expenses == null ){
            m_expenses = ExpenseManager.getInstance().getExpenses(this);
        }

        return m_expenses;
    }

    /**
     * Calculates how much of the budget remains for the given date.
     *
     * @param date The date to calculate the budget for.
     *
     * @return The remaining budget for the given date in pennies.
     */
    public int getRemainingBudget( Date date ){
        if( date.before( m_start ) || date.after( m_end ) ){
            // TODO: Make a real exception class to throw here.
            throw new RuntimeException( "Date is out of this budget's range" );
        }

        int remainingBudget = m_dailyLimit;
        for (Expense expense : m_expenses) {
            if( _datesEqual( date, expense.getDate() ) ){
                remainingBudget -= expense.getCost();
            }
        }

        return remainingBudget;
    }

    /**
     * Calculates the remaining budget in dollars.
     *
     * @param date The date to calculate the budget for.
     *
     * @return The remaining budget for the given date in dollars.
     */
    public double getRealRemainingBudget(Date date){
        return ((double)getRemainingBudget(date)) / 100.0;
    }

    private boolean _datesEqual( Date a, Date b ){
        return _zeroTime( a ) == _zeroTime( b );
    }

    private Date _zeroTime( Date date ){
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }
}
