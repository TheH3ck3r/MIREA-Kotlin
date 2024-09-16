package com.example.pr1

class ExpensesList {
    private val expenses: MutableList<Expense> = mutableListOf()

    // Добавление
    fun addExpense(expense: Expense) {
        expenses.add(expense)
    }

    // Показать все
    fun showAllExpenses(): String {
        return expenses.joinToString(separator = "\n") { expense ->
            expense.showInfo()
        }
    }

    // Подсчет суммы для каждой категории
    fun calculateTotalByCategory(): Map<String, Double> {
        val categoryTotals = mutableMapOf<String, Double>()
        for (expense in expenses) {
            categoryTotals[expense.Category] = categoryTotals.getOrDefault(expense.Category, 0.0) + expense.Sum
        }
        return categoryTotals
    }
}