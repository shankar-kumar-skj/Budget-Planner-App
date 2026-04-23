package com.budgetplan.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.budgetplan.app.data.model.Budget
import com.budgetplan.app.data.model.Category
import com.budgetplan.app.data.model.Transaction
import com.budgetplan.app.data.model.TransactionType
import com.budgetplan.app.data.repository.BudgetDatabase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar

class BudgetViewModel(application: Application) : AndroidViewModel(application) {
    private val db = BudgetDatabase.getDatabase(application)
    private val transactionDao = db.transactionDao()
    private val budgetDao = db.budgetDao()

    private val _selectedMonth = MutableStateFlow(Calendar.getInstance().get(Calendar.MONTH))
    val selectedMonth: StateFlow<Int> = _selectedMonth

    private val _selectedYear = MutableStateFlow(Calendar.getInstance().get(Calendar.YEAR))
    val selectedYear: StateFlow<Int> = _selectedYear

    val transactions: StateFlow<List<Transaction>> = combine(_selectedMonth, _selectedYear) { m, y -> Pair(m, y) }
        .flatMapLatest { (m, y) -> transactionDao.getTransactionsByMonth(m, y) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val totalIncome: StateFlow<Double> = combine(_selectedMonth, _selectedYear) { m, y -> Pair(m, y) }
        .flatMapLatest { (m, y) -> transactionDao.getTotalIncome(m, y).map { it ?: 0.0 } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val totalExpense: StateFlow<Double> = combine(_selectedMonth, _selectedYear) { m, y -> Pair(m, y) }
        .flatMapLatest { (m, y) -> transactionDao.getTotalExpense(m, y).map { it ?: 0.0 } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val balance: StateFlow<Double> = combine(totalIncome, totalExpense) { inc, exp -> inc - exp }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0.0)

    val budgets: StateFlow<List<Budget>> = combine(_selectedMonth, _selectedYear) { m, y -> Pair(m, y) }
        .flatMapLatest { (m, y) -> budgetDao.getBudgetsByMonth(m, y) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val expenseByCategory: StateFlow<Map<Category, Double>> = transactions
        .map { list ->
            list.filter { it.type == TransactionType.EXPENSE }
                .groupBy { it.category }
                .mapValues { (_, txns) -> txns.sumOf { it.amount } }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    fun setMonth(month: Int, year: Int) {
        _selectedMonth.value = month
        _selectedYear.value = year
    }

    fun addTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionDao.insertTransaction(transaction)
    }

    fun deleteTransaction(transaction: Transaction) = viewModelScope.launch {
        transactionDao.deleteTransaction(transaction)
    }

    fun addBudget(budget: Budget) = viewModelScope.launch {
        budgetDao.insertBudget(budget)
    }

    fun deleteBudget(budget: Budget) = viewModelScope.launch {
        budgetDao.deleteBudget(budget)
    }
}
