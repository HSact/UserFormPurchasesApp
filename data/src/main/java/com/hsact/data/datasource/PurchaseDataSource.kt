package com.hsact.data.datasource

import android.content.Context
import com.hsact.data.model.PurchaseDto
import com.hsact.data.model.PurchaseListDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PurchaseDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {
    fun loadFromAssets(): List<PurchaseDto> {
        val json = context.assets.open("mock_data.json")
            .bufferedReader()
            .use { it.readText() }

        val result = Json.decodeFromString<PurchaseListDto>(json)
        return result.data
    }
}