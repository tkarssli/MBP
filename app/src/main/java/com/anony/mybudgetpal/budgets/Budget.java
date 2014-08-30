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

    Budget( int id, int dailyLimit, Date start, Date end ){
        m_id            = id;
        m_dailyLimit    = dailyLimit;
        m_start         = start;
        m_end           = end;
    }

    public void addExpense(Expense expense){
        expense.setBudget(this);
        m_expenses.add(expense);
        ExpenseManager.getInstance().addExpense(expense);
    }

    public int getId(){
        return m_id;
    }

    public int getDailyLimit(){
        return m_dailyLimit;
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

    public int getRemainingBudget( Date date ) throws RuntimeException {
        if( date.before( m_start ) || date.after( m_end ) ){
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
