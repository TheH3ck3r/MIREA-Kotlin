package com.example.pr1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pr1.ui.theme.Pr1Theme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pr1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExpenseTrackerApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ExpenseTrackerApp(modifier: Modifier = Modifier) {
    val expensesList = remember { ExpensesList() }
    var sum by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var totals by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
    var allExpenses by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = sum,
            onValueChange = { sum = it },
            label = { Text("Сумма") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Категория") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
                val expense = Expense(sum.toDouble(), category, Date())
                expensesList.addExpense(expense)
                sum = ""
                category = ""
                allExpenses = expensesList.showAllExpenses()
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Добавить расход")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Все расходы:", style = MaterialTheme.typography.titleLarge)
        LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
            items(allExpenses.split("\n").reversed()) { expense ->
                Text(expense)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                totals = expensesList.calculateTotalByCategory()
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Подсчитать сумму по категориям")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Сумма по категориям:", style = MaterialTheme.typography.titleLarge)
        LazyColumn {
            items(totals.toList()) { (category, total) ->
                Text("Категория: $category, Сумма: $total")
            }
        }
    }
}

@Preview(showBackground = true)

@Composable
fun ExpenseTrackerAppPreview() {
    Pr1Theme {
        ExpenseTrackerApp()
    }
}