package com.budgetplan.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\b"}, d2 = {"Lcom/budgetplan/app/data/repository/BudgetDatabase;", "Landroidx/room/RoomDatabase;", "()V", "budgetDao", "Lcom/budgetplan/app/data/repository/BudgetDao;", "transactionDao", "Lcom/budgetplan/app/data/repository/TransactionDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.budgetplan.app.data.model.Transaction.class, com.budgetplan.app.data.model.Budget.class}, version = 1, exportSchema = false)
@androidx.room.TypeConverters(value = {com.budgetplan.app.data.repository.Converters.class})
public abstract class BudgetDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.budgetplan.app.data.repository.BudgetDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.budgetplan.app.data.repository.BudgetDatabase.Companion Companion = null;
    
    public BudgetDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgetplan.app.data.repository.TransactionDao transactionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.budgetplan.app.data.repository.BudgetDao budgetDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/budgetplan/app/data/repository/BudgetDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/budgetplan/app/data/repository/BudgetDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.budgetplan.app.data.repository.BudgetDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}