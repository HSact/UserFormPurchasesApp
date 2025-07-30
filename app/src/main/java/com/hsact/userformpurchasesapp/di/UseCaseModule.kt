package com.hsact.userformpurchasesapp.di

import com.hsact.domain.repository.PurchaseRepository
import com.hsact.domain.repository.UserDataRepository
import com.hsact.domain.usecase.GetPurchasesUseCase
import com.hsact.domain.usecase.userdata.GetUserDataUseCase
import com.hsact.domain.usecase.userdata.SaveUserDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSaveUserDataUseCase(
        repository: UserDataRepository
    ): SaveUserDataUseCase = SaveUserDataUseCase(repository)

    @Provides
    fun provideGetUserDataUseCase(
        repository: UserDataRepository
    ): GetUserDataUseCase = GetUserDataUseCase(repository)

    @Provides
    fun provideGetPurchasesUseCase(
        repository: PurchaseRepository
    ): GetPurchasesUseCase = GetPurchasesUseCase(repository)
}