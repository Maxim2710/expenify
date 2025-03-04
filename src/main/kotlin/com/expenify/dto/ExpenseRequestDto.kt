package com.expenify.dto

import com.expenify.model.enums.ExpenseCategory
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class ExpenseRequestDto(
    @field:NotNull(message = "Amount is required")
    val amount: BigDecimal,

    val description: String,

    @field:NotNull(message = "Date is required")
    val date: LocalDate,

    @field:NotNull(message = "Category is required")
    val category: ExpenseCategory
)
