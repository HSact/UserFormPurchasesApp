package com.hsact.data.di

import com.hsact.data.repository.UserDataRepositoryImpl
import com.hsact.domain.repository.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserDataRepositoryModule {

    @Binds
    @Singleton
    @Suppress("unused")
    abstract fun bindUserRepository(
        impl: UserDataRepositoryImpl
    ): UserDataRepository
}