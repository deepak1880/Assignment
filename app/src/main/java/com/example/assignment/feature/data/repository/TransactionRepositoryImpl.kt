package com.example.assignment.feature.data.repository

import com.example.assignment.feature.data.datasource.AndroidResourceDataSource
import com.example.assignment.feature.data.model.TransactionListDto
import com.example.assignment.feature.domain.model.MidGroup
import com.example.assignment.feature.domain.model.TidGroup
import com.example.assignment.feature.domain.model.Transaction
import com.example.assignment.feature.domain.repository.TransactionRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionRepositoryImpl(
    private val dataSource: AndroidResourceDataSource,
    private val rawResourceId: Int
) : TransactionRepository {

    override suspend fun getTransactionsGrouped(): List<MidGroup> = withContext(Dispatchers.IO) {
        val jsonString = dataSource.readRawJson(rawResourceId)
        val gson = Gson()
        val transactionList = gson.fromJson(jsonString, TransactionListDto::class.java)

        val transactions = transactionList.sort.map { dto ->
            Transaction(dto.Mid, dto.Tid, dto.amount, dto.narration)
        }

        transactions
            .groupBy { it.Mid }
            .map { (mid, txByMid) ->
                val tidGroups = txByMid.groupBy { it.Tid }
                    .map { (tid, txByTid) -> TidGroup(tid, txByTid) }
                MidGroup(mid, tidGroups)
            }
    }
}



