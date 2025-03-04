package com.expenify.repository

import com.expenify.model.Expense
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

@EnableR2dbcRepositories
interface ExpenseRepository : CoroutineCrudRepository<Expense, Long> {
}