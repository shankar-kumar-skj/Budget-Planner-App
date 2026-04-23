package com.budgetplan.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.budgetplan.app.data.model.Budget
import com.budgetplan.app.data.model.Category
import com.budgetplan.app.ui.theme.GreenPrimary
import com.budgetplan.app.ui.theme.RedContainer
import com.budgetplan.app.viewmodel.BudgetViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetScreen(viewModel: BudgetViewModel) {
    val budgets by viewModel.budgets.collectAsState()
    val expenseMap by viewModel.expenseByCategory.collectAsState()
    val selectedMonth by viewModel.selectedMonth.collectAsState()
    val selectedYear by viewModel.selectedYear.collectAsState()
    val months = listOf("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Budgets — ${months[selectedMonth]} $selectedYear", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = GreenPrimary, titleContentColor = Color.White)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }, containerColor = GreenPrimary) {
                Icon(Icons.Default.Add, "Add Budget", tint = Color.White)
            }
        }
    ) { padding ->
        if (budgets.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("📊", fontSize = 48.sp)
                    Spacer(Modifier.height(8.dp))
                    Text("No budgets set", fontWeight = FontWeight.SemiBold)
                    Text("Tap + to set a category budget", color = Color.Gray, fontSize = 14.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                items(budgets) { budget ->
                    val spent = expenseMap[budget.category] ?: 0.0
                    BudgetCard(budget, spent, onDelete = { viewModel.deleteBudget(budget) })
                }
            }
        }

        if (showDialog) {
            AddBudgetDialog(
                onDismiss = { showDialog = false },
                onAdd = { cat, limit ->
                    val cal = Calendar.getInstance()
                    viewModel.addBudget(Budget(category = cat, limit = limit, month = selectedMonth, year = selectedYear))
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun BudgetCard(budget: Budget, spent: Double, onDelete: () -> Unit) {
    val progress = (spent / budget.limit).toFloat().coerceIn(0f, 1f)
    val isOverBudget = spent > budget.limit
    val barColor = when {
        progress >= 1f -> MaterialTheme.colorScheme.error
        progress >= 0.8f -> Color(0xFFF57C00)
        else -> GreenPrimary
    }

    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), elevation = CardDefaults.cardElevation(2.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(budget.category.emoji, fontSize = 24.sp)
                Spacer(Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(budget.category.label, fontWeight = FontWeight.SemiBold)
                    Text("Limit: ₹${String.format("%,.2f", budget.limit)}", color = Color.Gray, fontSize = 12.sp)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, "Delete", tint = Color.Gray)
                }
            }
            Spacer(Modifier.height(10.dp))
            Box(modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)).background(Color(0xFFE0E0E0))) {
                Box(modifier = Modifier.fillMaxWidth(progress).height(8.dp).clip(RoundedCornerShape(4.dp)).background(barColor))
            }
            Spacer(Modifier.height(6.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(if (isOverBudget) "Over budget!" else "₹${String.format("%,.2f", budget.limit - spent)} left",
                    color = if (isOverBudget) MaterialTheme.colorScheme.error else Color.Gray, fontSize = 12.sp)
                Text("₹${String.format("%,.2f", spent)} spent", fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBudgetDialog(onDismiss: () -> Unit, onAdd: (Category, Double) -> Unit) {
    var limitText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(Category.FOOD) }
    var expanded by remember { mutableStateOf(false) }
    val expenseCategories = Category.values().filter { it.name.startsWith("OTHER") || !it.name.startsWith("SALARY") && !it.name.startsWith("FREELANCE") && !it.name.startsWith("INVESTMENT") && it != Category.OTHER_INCOME }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Set Budget") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                    OutlinedTextField(
                        value = "${selectedCategory.emoji} ${selectedCategory.label}",
                        onValueChange = {}, readOnly = true,
                        label = { Text("Category") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        expenseCategories.forEach { cat ->
                            DropdownMenuItem(
                                text = { Text("${cat.emoji} ${cat.label}") },
                                onClick = { selectedCategory = cat; expanded = false }
                            )
                        }
                    }
                }
                OutlinedTextField(
                    value = limitText, onValueChange = { limitText = it },
                    label = { Text("Monthly Limit (₹)") }, modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), singleLine = true
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val limit = limitText.toDoubleOrNull()
                if (limit != null && limit > 0) onAdd(selectedCategory, limit)
            }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } }
    )
}
