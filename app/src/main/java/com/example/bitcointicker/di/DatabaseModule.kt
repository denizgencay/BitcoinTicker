package com.example.bitcointicker.di

import com.example.bitcointicker.data.repository.DatabaseRepositoryImpl
import com.example.bitcointicker.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseRepository(): DatabaseRepository{
        return DatabaseRepositoryImpl()
    }
}