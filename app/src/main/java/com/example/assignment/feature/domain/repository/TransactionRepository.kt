package com.example.assignment.feature.domain.repository

import com.example.assignment.feature.domain.model.MidGroup

interface TransactionRepository {
    suspend fun getTransactionsGrouped(): List<MidGroup>
}