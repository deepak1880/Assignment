package com.example.assignment.feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.feature.domain.model.MidGroup
import com.example.assignment.feature.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.launch

class TransactionViewModel(private val getTransactionsUseCase: GetTransactionsUseCase) :
    ViewModel() {
    private val _midGroups = MutableLiveData<List<MidGroup>>()
    val midGroups: LiveData<List<MidGroup>> = _midGroups

    fun loadTransactions() {
        viewModelScope.launch {
            try {
                val grouped = getTransactionsUseCase()
                _midGroups.value = grouped
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
