package com.budgetplan.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
fun TransactionDetailScreen(transaction: Transaction, viewModel: BudgetViewModel, onBack: () -> Unit) {
    val isIncome = transaction.type == TransactionType.INCOME
    val bgColor = if (isIncome) GreenContainer else RedContainer
    val amtColor = if (isIncome) Color(0xFF006C42) else Color(0xFF93000A)
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transaction Detail", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back", tint = Color.White) }
                },
                actions = {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(Icons.Default.Delete, "Delete", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GreenPrimary, titleContentColor = Color.White)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = bgColor)) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(64.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.5f)), contentAlignment = Alignment.Center) {
                        Text(transaction.category.emoji, fontSize = 30.sp)
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(transaction.title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = amtColor)
                    Spacer(Modifier.height(4.dp))
                    Text("${if (isIncome) "+" else "-"}₹${String.format("%,.2f", transaction.amount)}", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = amtColor)
                }
            }
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    DetailRow("Type", transaction.type.name.lowercase().replaceFirstChar { it.uppercase() })
                    HorizontalDivider()
                    DetailRow("Category", "${transaction.category.emoji} ${transaction.category.label}")
                    HorizontalDivider()
                    DetailRow("Date", SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date(transaction.date)))
                    if (transaction.note.isNotBlank()) {
                        HorizontalDivider()
                        DetailRow("Note", transaction.note)
                    }
                }
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Transaction") },
            text = { Text("Are you sure you want to delete \"${transaction.title}\"?") },
            confirmButton = {
                TextButton(onClick = { viewModel.deleteTransaction(transaction); onBack() }, colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)) {
                    Text("Delete")
                }
            },
            dismissButton = { TextButton(onClick = { showDeleteDialog = false }) { Text("Cancel") } }
        )
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}
