package com.hsact.data.datasource

import android.content.Context
import com.hsact.data.model.PurchaseDto
import com.hsact.data.model.PurchaseListDto
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class PurchaseDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {
    fun loadFromAssets(): Flow<List<PurchaseDto>> = flow {
        val json = context.assets.open("mock_data.json")
            .bufferedReader()
            .use { it.readText() }

        val result = Json.decodeFromString<PurchaseListDto>(json)
        emit(result.data)
    }
}