package com.budgetplan.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class TransactionType { INCOME, EXPENSE }

enum class Category(val label: String, val emoji: String) {
    SALARY("Salary", "💼"),
    FREELANCE("Freelance", "💻"),
    INVESTMENT("Investment", "📈"),
    OTHER_INCOME("Other Income", "💰"),
    FOOD("Food & Dining", "🍔"),
    TRANSPORT("Transport", "🚗"),
    SHOPPING("Shopping", "🛍️"),
    ENTERTAINMENT("Entertainment", "🎬"),
    HEALTH("Health", "🏥"),
    EDUCATION("Education", "📚"),
    UTILITIES("Utilities", "💡"),
    RENT("Rent", "🏠"),
    OTHER_EXPENSE("Other Expense", "📦")
}

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val amount: Double,
    val type: TransactionType,
    val category: Category,
    val note: String = "",
    val date: Long = System.currentTimeMillis(),
    val month: Int = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH),
    val year: Int = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
)

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: Category,
    val limit: Double,
    val month: Int,
    val year: Int
)
