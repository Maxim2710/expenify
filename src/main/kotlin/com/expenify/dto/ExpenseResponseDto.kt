package com.expenify.dto

import com.expenify.model.enums.ExpenseCategory
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class ExpenseResponseDto(
    val id: Long?,
    val amount: BigDecimal,
    val description: String,
    val date: LocalDate,
    val category: ExpenseCategory,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)