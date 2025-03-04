package com.expenify.controller

import com.expenify.dto.ExpenseRequestDto
import com.expenify.dto.ExpenseResponseDto
import com.expenify.service.ExpenseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/expenses")
@Validated
class ExpenseController(
    private val expenseService: ExpenseService
) {

    @PostMapping
    suspend fun createExpense(
        @Valid @RequestBody expenseRequest: ExpenseRequestDto
    ): ResponseEntity<ExpenseResponseDto> {
        val response = expenseService.createExpense(expenseRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}