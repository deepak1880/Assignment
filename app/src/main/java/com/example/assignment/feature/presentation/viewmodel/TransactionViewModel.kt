package com.example.assignment.feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.feature.data.model.MidGroup
import com.example.assignment.feature.data.repository.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(private val repository: TransactionRepository) : ViewModel() {

    private val _midGroups = MutableLiveData<List<MidGroup>>()
    val midGroups: LiveData<List<MidGroup>> = _midGroups

    fun loadTransactions() {
        viewModelScope.launch {
            val grouped = repository.getTransactions()
            _midGroups.value = grouped
        }
    }
}
