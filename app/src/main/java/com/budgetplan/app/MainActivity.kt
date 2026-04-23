package com.budgetplan.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.budgetplan.app.data.model.Transaction
import com.budgetplan.app.ui.screens.*
import com.budgetplan.app.ui.theme.BudgetPlanTheme
import com.budgetplan.app.viewmodel.BudgetViewModel
import com.google.gson.Gson

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Dashboard : Screen("dashboard", "Home", Icons.Default.Home)
    object Analytics : Screen("analytics", "Analytics", Icons.Default.Analytics)
    object Budgets : Screen("budgets", "Budgets", Icons.Default.Savings)
    object AddTransaction : Screen("add_transaction", "Add", Icons.Default.Home)
    object TransactionDetail : Screen("transaction_detail/{id}", "Detail", Icons.Default.Home)
}

val bottomNavItems = listOf(Screen.Dashboard, Screen.Analytics, Screen.Budgets)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BudgetPlanTheme {
                BudgetApp()
            }
        }
    }
}

@Composable
fun BudgetApp() {
    val navController = rememberNavController()
    val viewModel: BudgetViewModel = viewModel()
    val transactions by viewModel.transactions.collectAsState()

    val navBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStack?.destination?.route
    val showBottomBar = currentRoute in bottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomNavItems.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, screen.label) },
                            label = { Text(screen.label) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen(
                    viewModel = viewModel,
                    onAddClick = { navController.navigate(Screen.AddTransaction.route) },
                    onTransactionClick = { txn ->
                        navController.navigate("transaction_detail/${txn.id}")
                    }
                )
            }
            composable(Screen.Analytics.route) {
                AnalyticsScreen(viewModel = viewModel)
            }
            composable(Screen.Budgets.route) {
                BudgetScreen(viewModel = viewModel)
            }
            composable(Screen.AddTransaction.route) {
                AddTransactionScreen(viewModel = viewModel, onBack = { navController.popBackStack() })
            }
            composable(
                route = "transaction_detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: return@composable
                val txn = transactions.find { it.id == id }
                if (txn != null) {
                    TransactionDetailScreen(
                        transaction = txn,
                        viewModel = viewModel,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}
