package com.example.assignment.feature.domain.usecase

import com.example.assignment.feature.domain.model.MidGroup
import com.example.assignment.feature.domain.repository.TransactionRepository

class GetTransactionsUseCase(private val repository: TransactionRepository) {
    suspend operator fun invoke(): List<MidGroup> {
        return repository.getTransactionsGrouped()
    }
}