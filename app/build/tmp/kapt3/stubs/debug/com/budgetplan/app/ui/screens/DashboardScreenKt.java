package com.budgetplan.app.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a2\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\b\u0010\t\u001a\u00020\u0001H\u0007\u001aB\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u0017\u001a \u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0007\u001a$\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\b2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001f"}, d2 = {"DashboardScreen", "", "viewModel", "Lcom/budgetplan/app/viewmodel/BudgetViewModel;", "onAddClick", "Lkotlin/Function0;", "onTransactionClick", "Lkotlin/Function1;", "Lcom/budgetplan/app/data/model/Transaction;", "EmptyState", "MiniCard", "label", "", "amount", "", "bg", "Landroidx/compose/ui/graphics/Color;", "textColor", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "modifier", "Landroidx/compose/ui/Modifier;", "MiniCard-9z6LAg8", "(Ljava/lang/String;DJJLandroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/ui/Modifier;)V", "SummaryCards", "balance", "income", "expense", "TransactionItem", "transaction", "onClick", "app_debug"})
public final class DashboardScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DashboardScreen(@org.jetbrains.annotations.NotNull()
    com.budgetplan.app.viewmodel.BudgetViewModel viewModel, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.budgetplan.app.data.model.Transaction, kotlin.Unit> onTransactionClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SummaryCards(double balance, double income, double expense) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void TransactionItem(@org.jetbrains.annotations.NotNull()
    com.budgetplan.app.data.model.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.budgetplan.app.data.model.Transaction, kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void EmptyState() {
    }
}