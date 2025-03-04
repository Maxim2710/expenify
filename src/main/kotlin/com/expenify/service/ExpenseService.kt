package com.expenify.service

import com.expenify.dto.ExpenseRequestDto
import com.expenify.dto.ExpenseResponseDto
import com.expenify.model.Expense
import com.expenify.repository.ExpenseRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository
) {
    suspend fun createExpense(expenseRequest: ExpenseRequestDto): ExpenseResponseDto? {
        val expense = Expense(
            amount = expenseRequest.amount,
            description = expenseRequest.description,
            date = expenseRequest.date,
            category = expenseRequest.category,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val savedExpense = expenseRepository.save(expense)

        return savedExpense.toResponse()
    }

    private fun Expense.toResponse() = ExpenseResponseDto(
        id = this.id,
        amount = this.amount,
        description = this.description,
        date = this.date,
        category = this.category,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}