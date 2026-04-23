package com.budgetplan.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.budgetplan.app.data.model.Category
import com.budgetplan.app.data.model.TransactionType
import com.budgetplan.app.ui.theme.*
import com.budgetplan.app.viewmodel.BudgetViewModel

val categoryColors = listOf(
    Color(0xFF1B8A5A), Color(0xFF1565C0), Color(0xFFE53935), Color(0xFFF57C00),
    Color(0xFF7B1FA2), Color(0xFF00838F), Color(0xFF558B2F), Color(0xFFAD1457),
    Color(0xFF4527A0), Color(0xFF2E7D32), Color(0xFF6D4C41), Color(0xFF37474F)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsScreen(viewModel: BudgetViewModel) {
    val expenseMap by viewModel.expenseByCategory.collectAsState()
    val totalIncome by viewModel.totalIncome.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()
    val transactions by viewModel.transactions.collectAsState()
    val selectedMonth by viewModel.selectedMonth.collectAsState()
    val selectedYear by viewModel.selectedYear.collectAsState()
    val months = listOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")

    val sortedExpenses = expenseMap.entries.sortedByDescending { it.value }
    val totalExp = sortedExpenses.sumOf { it.value }.takeIf { it > 0 } ?: 1.0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Analytics — ${months[selectedMonth]} $selectedYear", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GreenPrimary, titleContentColor = Color.White)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    StatCard("Total Income", totalIncome, GreenContainer, Color(0xFF006C42), Modifier.weight(1f))
                    StatCard("Total Expense", totalExpense, RedContainer, Color(0xFF93000A), Modifier.weight(1f))
                }
            }
            item {
                val savings = totalIncome - totalExpense
                val savingsRate = if (totalIncome > 0) (savings / totalIncome * 100) else 0.0
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Savings Rate", fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(8.dp))
                        LinearProgressIndicator(
                            progress = { (savingsRate / 100).toFloat().coerceIn(0f, 1f) },
                            modifier = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(5.dp)),
                            color = if (savingsRate >= 20) GreenPrimary else MaterialTheme.colorScheme.error,
                            trackColor = Color(0xFFE0E0E0)
                        )
                        Spacer(Modifier.height(6.dp))
                        Text("${String.format("%.1f", savingsRate)}% of income saved  ·  ₹${String.format("%,.2f", savings)}", fontSize = 13.sp, color = Color.Gray)
                    }
                }
            }
            if (sortedExpenses.isNotEmpty()) {
                item { Text("Spending by Category", fontWeight = FontWeight.SemiBold, fontSize = 16.sp) }
                items(sortedExpenses.mapIndexed { i, e -> Pair(i, e) }) { (idx, entry) ->
                    CategoryBar(entry.key, entry.value, totalExp, categoryColors[idx % categoryColors.size])
                }
            }
            item {
                val incomeCount = transactions.count { it.type == TransactionType.INCOME }
                val expCount = transactions.count { it.type == TransactionType.EXPENSE }
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("This Month", fontWeight = FontWeight.SemiBold)
                        HorizontalDivider()
                        StatRow("Total Transactions", "${transactions.size}")
                        StatRow("Income Entries", "$incomeCount")
                        StatRow("Expense Entries", "$expCount")
                        if (expCount > 0) StatRow("Avg Expense", "₹${String.format("%,.2f", totalExpense / expCount)}")
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: Double, bg: Color, textColor: Color, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = bg), shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(label, color = textColor, fontSize = 13.sp)
            Spacer(Modifier.height(4.dp))
            Text("₹${String.format("%,.0f", value)}", color = textColor, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}

@Composable
fun CategoryBar(category: Category, amount: Double, total: Double, color: Color) {
    val ratio = (amount / total).toFloat()
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), elevation = CardDefaults.cardElevation(1.dp)) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(category.emoji, fontSize = 18.sp)
                    Spacer(Modifier.width(8.dp))
                    Text(category.label, fontWeight = FontWeight.Medium)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text("₹${String.format("%,.2f", amount)}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text("${String.format("%.1f", ratio * 100)}%", color = Color.Gray, fontSize = 12.sp)
                }
            }
            Spacer(Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)).background(Color(0xFFE0E0E0))) {
                Box(modifier = Modifier.fillMaxWidth(ratio).height(6.dp).clip(RoundedCornerShape(3.dp)).background(color))
            }
        }
    }
}

@Composable
fun StatRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}
