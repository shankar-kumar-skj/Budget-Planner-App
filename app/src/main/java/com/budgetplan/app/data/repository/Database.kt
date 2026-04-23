package com.budgetplan.app.data.repository

import androidx.room.*
import com.budgetplan.app.data.model.Budget
import com.budgetplan.app.data.model.Category
import com.budgetplan.app.data.model.Transaction
import com.budgetplan.app.data.model.TransactionType
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE month = :month AND year = :year ORDER BY date DESC")
    fun getTransactionsByMonth(month: Int, year: Int): Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE type = :type AND month = :month AND year = :year ORDER BY date DESC")
    fun getTransactionsByType(type: TransactionType, month: Int, year: Int): Flow<List<Transaction>>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'INCOME' AND month = :month AND year = :year")
    fun getTotalIncome(month: Int, year: Int): Flow<Double?>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'EXPENSE' AND month = :month AND year = :year")
    fun getTotalExpense(month: Int, year: Int): Flow<Double?>

    @Query("SELECT SUM(amount) FROM transactions WHERE category = :category AND type = 'EXPENSE' AND month = :month AND year = :year")
    fun getSpentByCategory(category: String, month: Int, year: Int): Flow<Double?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)
}

@Dao
interface BudgetDao {
    @Query("SELECT * FROM budgets WHERE month = :month AND year = :year")
    fun getBudgetsByMonth(month: Int, year: Int): Flow<List<Budget>>

    @Query("SELECT * FROM budgets WHERE category = :category AND month = :month AND year = :year LIMIT 1")
    suspend fun getBudgetForCategory(category: String, month: Int, year: Int): Budget?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: Budget)

    @Delete
    suspend fun deleteBudget(budget: Budget)
}

@TypeConverters
class Converters {
    @TypeConverter
    fun fromTransactionType(value: TransactionType): String = value.name

    @TypeConverter
    fun toTransactionType(value: String): TransactionType = TransactionType.valueOf(value)

    @TypeConverter
    fun fromCategory(value: Category): String = value.name

    @TypeConverter
    fun toCategory(value: String): Category = Category.valueOf(value)
}

@Database(entities = [Transaction::class, Budget::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BudgetDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun budgetDao(): BudgetDao

    companion object {
        @Volatile private var INSTANCE: BudgetDatabase? = null

        fun getDatabase(context: android.content.Context): BudgetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BudgetDatabase::class.java,
                    "budget_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
