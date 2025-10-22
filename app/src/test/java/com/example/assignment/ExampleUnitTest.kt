package com.example.assignment

import com.example.assignment.feature.data.model.MidGroup
import com.example.assignment.feature.data.model.TidGroup
import com.example.assignment.feature.data.model.Transaction
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun groupedTransactions() {
        val transactions = listOf(
            Transaction(Mid = 1, Tid = 1, amount = 10.0, narration = 11234684654),
            Transaction(Mid = 1, Tid = 2, amount = 20.0, narration = 11234684654),
            Transaction(Mid = 2, Tid = 1, amount = 30.0, narration = 11234684654)
        )

        val expected = listOf(
            MidGroup(1, listOf(
                TidGroup(1, listOf(transactions[0])),
                TidGroup(2, listOf(transactions[1]))
            )),
            MidGroup(2, listOf(
                TidGroup(1, listOf(transactions[2]))
            ))
        )
        val actual = transactions
            .groupBy { it.Mid }
            .map { (mid, transByMid) ->
                val tidGroups = transByMid
                    .groupBy { it.Tid }
                    .map { (tid, transByTid) -> TidGroup(tid, transByTid) }
                MidGroup(mid, tidGroups)
            }
        assertEquals(expected, actual)
    }
}