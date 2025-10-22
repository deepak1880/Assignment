package com.example.assignment.feature.data.repository

import com.example.assignment.R
import android.content.Context
import com.example.assignment.feature.data.model.MidGroup
import com.example.assignment.feature.data.model.TidGroup
import com.example.assignment.feature.data.model.TransactionList
import com.google.gson.Gson

class TransactionRepository(private val context: Context) {

    fun getTransactions(): List<MidGroup> {
        val jsonString = context.resources.openRawResource(R.raw.data)
            .bufferedReader().use { it.readText() }

        val gson = Gson()
        val transactionList = gson.fromJson(jsonString, TransactionList::class.java)

        return transactionList.sort
            .groupBy { it.Mid }
            .map { (mid, transactionsByMid) ->
                val tidGroups = transactionsByMid
                    .groupBy { it.Tid }
                    .map { (tid, transactionsByTid) ->
                        TidGroup(tid, transactionsByTid)
                    }
                MidGroup(mid, tidGroups)
            }
    }
}
