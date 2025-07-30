package com.hsact.data.di

import com.hsact.data.repository.PurchaseRepositoryImpl
import com.hsact.domain.repository.PurchaseRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPurchaseRepository(
        impl: PurchaseRepositoryImpl
    ): PurchaseRepository
}