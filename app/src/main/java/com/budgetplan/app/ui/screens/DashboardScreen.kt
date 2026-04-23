package com.budgetplan.app.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.budgetplan.app.data.model.Transaction
import com.budgetplan.app.data.model.TransactionType
import com.budgetplan.app.ui.theme.*
import com.budgetplan.app.viewmodel.BudgetViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: BudgetViewModel, onAddClick: () -> Unit, onTransactionClick: (Transaction) -> Unit) {
    val transactions by viewModel.transactions.collectAsState()
    val totalIncome by viewModel.totalIncome.collectAsState()
    val totalExpense by viewModel.totalExpense.collectAsState()
    val balance by viewModel.balance.collectAsState()
    val selectedMonth by viewModel.selectedMonth.collectAsState()
    val selectedYear by viewModel.selectedYear.collectAsState()

    val months = listOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Budget Planner", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenPrimary, titleContentColor = Color.White
                ),
                actions = {
                    var expanded by remember { mutableStateOf(false) }
                    TextButton(onClick = { expanded = true }) {
                        Text("${months[selectedMonth]} $selectedYear", color = Color.White)
                        Icon(Icons.Default.ArrowDropDown, null, tint = Color.White)
                    }
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        val cal = Calendar.getInstance()
                        repeat(12) { i ->
                            val m = (cal.get(Calendar.MONTH) - i + 12) % 12
                            val y = cal.get(Calendar.YEAR) - if (cal.get(Calendar.MONTH) - i < 0) 1 else 0
                            DropdownMenuItem(
                                text = { Text("${months[m]} $y") },
                                onClick = { viewModel.setMonth(m, y); expanded = false }
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick, containerColor = GreenPrimary) {
                Icon(Icons.Default.Add, "Add", tint = Color.White)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item { SummaryCards(balance, totalIncome, totalExpense) }
            item {
                if (transactions.isEmpty()) {
                    EmptyState()
                } else {
                    Text("Recent Transactions", fontWeight = FontWeight.SemiBold, fontSize = 17.sp, modifier = Modifier.padding(vertical = 4.dp))
                }
            }
            items(transactions.take(20)) { txn ->
                TransactionItem(txn, onTransactionClick)
            }
        }
    }
}

@Composable
fun SummaryCards(balance: Double, income: Double, expense: Double) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = GreenPrimary),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Current Balance", color = Color.White.copy(alpha = 0.85f), fontSize = 14.sp)
                Spacer(Modifier.height(4.dp))
                Text("₹ ${String.format("%,.2f", balance)}", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            MiniCard("Income", income, GreenContainer, Color(0xFF006C42), Icons.Default.TrendingUp, Modifier.weight(1f))
            MiniCard("Expense", expense, RedContainer, Color(0xFF93000A), Icons.Default.TrendingDown, Modifier.weight(1f))
        }
    }
}

@Composable
fun MiniCard(label: String, amount: Double, bg: Color, textColor: Color, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = bg), shape = RoundedCornerShape(16.dp)) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = textColor, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(4.dp))
                Text(label, color = textColor, fontSize = 13.sp)
            }
            Spacer(Modifier.height(4.dp))
            Text("₹ ${String.format("%,.2f", amount)}", color = textColor, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction, onClick: (Transaction) -> Unit) {
    val isIncome = transaction.type == TransactionType.INCOME
    val bgColor = if (isIncome) GreenContainer else RedContainer
    val amtColor = if (isIncome) Color(0xFF006C42) else Color(0xFF93000A)
    val sign = if (isIncome) "+" else "-"
    val dateStr = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(transaction.date))

    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick(transaction) },
        colors = CardDefaults.cardColors(containerColor = CardColor),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier.size(44.dp).clip(CircleShape).background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Text(transaction.category.emoji, fontSize = 20.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(transaction.title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("${transaction.category.label} · $dateStr", color = Color.Gray, fontSize = 12.sp)
            }
            Text("$sign₹${String.format("%,.2f", transaction.amount)}", color = amtColor, fontWeight = FontWeight.Bold, fontSize = 15.sp)
        }
    }
}

@Composable
fun EmptyState() {
    Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("💰", fontSize = 48.sp)
            Spacer(Modifier.height(8.dp))
            Text("No transactions yet", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            Text("Tap + to add your first entry", color = Color.Gray, fontSize = 14.sp)
        }
    }
}
