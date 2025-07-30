package com.hsact.userformpurchasesapp.di

import com.hsact.domain.repository.PurchaseRepository
import com.hsact.domain.usecase.GetPurchasesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPurchasesUseCase(
        repository: PurchaseRepository
    ): GetPurchasesUseCase = GetPurchasesUseCase(repository)
}