package com.expenify.service

import com.expenify.dto.ExpenseFilterDto
import com.expenify.dto.ExpenseRequestDto
import com.expenify.dto.ExpenseResponseDto
import com.expenify.exception.ExpenseNotFoundException
import com.expenify.model.Expense
import com.expenify.model.enums.ExpenseCategory
import com.expenify.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository
) {
    suspend fun createExpense(expenseRequest: ExpenseRequestDto): ExpenseResponseDto {
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

    suspend fun getExpenses(expenseFilter: ExpenseFilterDto): Flow<ExpenseResponseDto> {
        return expenseRepository.findAll()
            .filter { expense ->
                (expenseFilter.startDate == null || !expense.date.isBefore(expenseFilter.startDate)) &&
                (expenseFilter.endDate == null || !expense.date.isAfter(expenseFilter.endDate)) &&
                (expenseFilter.category == null || expense.category == expenseFilter.category)
            }
            .map { it.toResponse() }
    }

    suspend fun getExpenseById(id: Long): ExpenseResponseDto {
        val expense = expenseRepository.findById(id)
            ?: throw ExpenseNotFoundException("Expense not found with id: $id")

        return expense.toResponse()
    }

    suspend fun updateExpense(id: Long, expenseRequest: ExpenseRequestDto): ExpenseResponseDto {
        val expense = expenseRepository.findById(id)
            ?: throw ExpenseNotFoundException("Expense not found with id: $id")

        val updatedExpense = expense.copy(
            amount = expenseRequest.amount,
            description = expenseRequest.description,
            date = expenseRequest.date,
            category = expenseRequest.category,
            updatedAt = LocalDateTime.now()
        )

        val savedExpense = expenseRepository.save(updatedExpense)

        return savedExpense.toResponse()
    }

    suspend fun deleteExpense(id: Long) {
        val expense = expenseRepository.findById(id)
            ?: throw ExpenseNotFoundException("Expense not found with id: $id")

        expenseRepository.delete(expense)
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