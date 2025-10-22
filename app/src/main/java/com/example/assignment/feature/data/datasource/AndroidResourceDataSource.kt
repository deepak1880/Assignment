package com.example.assignment.feature.data.datasource

import android.content.Context

class AndroidResourceDataSource(private val context: Context) {
    fun readRawJson(resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader().use { it.readText() }
    }
}