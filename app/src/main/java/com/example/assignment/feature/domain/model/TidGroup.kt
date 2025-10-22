package com.example.assignment.feature.domain.model

data class TidGroup(
    val tid: Long,
    val transactions: List<Transaction>
)