package com.hsact.userformpurchasesapp.di

import com.hsact.domain.repository.PurchaseRepository
import com.hsact.domain.repository.UserDataRepository
import com.hsact.domain.usecase.purchases.GetGroupedPurchasesUseCase
import com.hsact.domain.usecase.userdata.GetUserDataUseCase
import com.hsact.domain.usecase.userdata.SaveUserDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSaveUserDataUseCase(
        repository: UserDataRepository
    ): SaveUserDataUseCase = SaveUserDataUseCase(repository)

    @Provides
    @Singleton
    fun provideGetUserDataUseCase(
        repository: UserDataRepository
    ): GetUserDataUseCase = GetUserDataUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPurchasesUseCase(
        repository: PurchaseRepository
    ): GetGroupedPurchasesUseCase = GetGroupedPurchasesUseCase(repository)
}