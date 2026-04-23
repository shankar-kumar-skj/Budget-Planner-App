package com.budgetplan.app.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a&\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0007\u00a8\u0006\u0010"}, d2 = {"AddBudgetDialog", "", "onDismiss", "Lkotlin/Function0;", "onAdd", "Lkotlin/Function2;", "Lcom/budgetplan/app/data/model/Category;", "", "BudgetCard", "budget", "Lcom/budgetplan/app/data/model/Budget;", "spent", "onDelete", "BudgetScreen", "viewModel", "Lcom/budgetplan/app/viewmodel/BudgetViewModel;", "app_debug"})
public final class BudgetScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void BudgetScreen(@org.jetbrains.annotations.NotNull()
    com.budgetplan.app.viewmodel.BudgetViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void BudgetCard(@org.jetbrains.annotations.NotNull()
    com.budgetplan.app.data.model.Budget budget, double spent, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void AddBudgetDialog(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super com.budgetplan.app.data.model.Category, ? super java.lang.Double, kotlin.Unit> onAdd) {
    }
}