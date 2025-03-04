package com.expenify.dto

import com.expenify.model.enums.ExpenseCategory
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class ExpenseFilterDto(
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val startDate: LocalDate? = null,

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    val endDate: LocalDate? = null,

    val category: ExpenseCategory? = null
)
