package com.anony.mybudgetpal;

import com.anony.mybudgetpal.budgets.BudgetManager;
import com.anony.mybudgetpal.db.BudgetsDatabase;

/**
 * Created by Natalie on 8/27/2014.
 */
public class Application extends android.app.Application {
    private static Application s_instance = null;
    private BudgetsDatabase m_budgetsDB = null;

    public Application(){
        super();

        // Initialize all the singletons.
        s_instance = this;
        m_budgetsDB = new BudgetsDatabase(this);
        BudgetManager.getInstance();
    }

    public static Application getInstance(){
        return s_instance;
    }

    public BudgetsDatabase getBudgetsDB(){
        return m_budgetsDB;
    }
}
