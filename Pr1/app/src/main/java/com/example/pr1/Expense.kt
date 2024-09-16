package com.example.pr1

import java.util.Date
import java.util.UUID

class Expense {
    val Id: UUID = UUID.randomUUID()
    var Sum: Double
    var Category: String
    var Date: Date

    constructor(Sum: Double, Category: String, Date: Date){
        this.Sum = Sum
        this.Category = Category
        this.Date = Date
    }

    fun showInfo(): String {
        return "Сумма: $Sum | Категория: $Category | Дата: $Date"
    }
}