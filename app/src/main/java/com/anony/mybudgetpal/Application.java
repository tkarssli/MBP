package com.anony.mybudgetpal;

import com.anony.mybudgetpal.budgets.BudgetManager;
import com.anony.mybudgetpal.db.BudgetsHelper;

/**
 * Created by Natalie on 8/27/2014.
 */
public class Application extends android.app.Application {
    private static Application s_instance = null;
    private BudgetsHelper m_budgetsDB = new BudgetsHelper(getApplicationContext());

    Application(){
        super();

        // Initialize all the singletons.
        s_instance = this;
        BudgetManager.getInstance();
    }

    public static Application getInstance(){
        return s_instance;
    }

    public BudgetsHelper getBudgetsDB(){
        return m_budgetsDB;
    }
}
