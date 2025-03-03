package com.expenify.model

import com.expenify.model.enums.ExpenseCategory
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Table(name = "expenses")
data class Expense(
    @Id
    val id: Long? = null,
    val amount: BigDecimal,
    val description: String,
    val date: LocalDate,
    val category: ExpenseCategory,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
