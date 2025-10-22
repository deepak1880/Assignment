package com.example.assignment.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.feature.domain.usecase.GetTransactionsUseCase

class TransactionViewModelFactory(
    private val getTransactionsUseCase: GetTransactionsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TransactionViewModel(getTransactionsUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
