package com.budgetplan.app.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0012J\u000e\u0010*\u001a\u00020(2\u0006\u0010+\u001a\u00020%J\u000e\u0010,\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0012J\u000e\u0010-\u001a\u00020(2\u0006\u0010+\u001a\u00020%J\u0016\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u0007R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000b0\u00170\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\rR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\rR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\rR\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\rR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\rR\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\u00110\n\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\r\u00a8\u00062"}, d2 = {"Lcom/budgetplan/app/viewmodel/BudgetViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_selectedMonth", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_selectedYear", "balance", "Lkotlinx/coroutines/flow/StateFlow;", "", "getBalance", "()Lkotlinx/coroutines/flow/StateFlow;", "budgetDao", "Lcom/budgetplan/app/data/repository/BudgetDao;", "budgets", "", "Lcom/budgetplan/app/data/model/Budget;", "getBudgets", "db", "Lcom/budgetplan/app/data/repository/BudgetDatabase;", "expenseByCategory", "", "Lcom/budgetplan/app/data/model/Category;", "getExpenseByCategory", "selectedMonth", "getSelectedMonth", "selectedYear", "getSelectedYear", "totalExpense", "getTotalExpense", "totalIncome", "getTotalIncome", "transactionDao", "Lcom/budgetplan/app/data/repository/TransactionDao;", "transactions", "Lcom/budgetplan/app/data/model/Transaction;", "getTransactions", "addBudget", "Lkotlinx/coroutines/Job;", "budget", "addTransaction", "transaction", "deleteBudget", "deleteTransaction", "setMonth", "", "month", "year", "app_debug"})
public final class BudgetViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.budgetplan.app.data.repository.BudgetDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetplan.app.data.repository.TransactionDao transactionDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.budgetplan.app.data.repository.BudgetDao budgetDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _selectedMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> selectedMonth = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _selectedYear = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> selectedYear = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.budgetplan.app.data.model.Transaction>> transactions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> totalIncome = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> totalExpense = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Double> balance = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.budgetplan.app.data.model.Budget>> budgets = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<com.budgetplan.app.data.model.Category, java.lang.Double>> expenseByCategory = null;
    
    public BudgetViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getSelectedMonth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getSelectedYear() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.budgetplan.app.data.model.Transaction>> getTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getTotalIncome() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getTotalExpense() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Double> getBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.budgetplan.app.data.model.Budget>> getBudgets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<com.budgetplan.app.data.model.Category, java.lang.Double>> getExpenseByCategory() {
        return null;
    }
    
    public final void setMonth(int month, int year) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job addTransaction(@org.jetbrains.annotations.NotNull()
    com.budgetplan.app.data.model.Transaction transaction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteTransaction(@org.jetbrains.annotations.NotNull()
    com.budgetplan.app.data.model.Transaction transaction) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job addBudget(@org.jetbrains.annotations.NotNull()
    com.budgetplan.app.data.model.Budget budget) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteBudget(@org.jetbrains.annotations.NotNull()
    com.budgetplan.app.data.model.Budget budget) {
        return null;
    }
}