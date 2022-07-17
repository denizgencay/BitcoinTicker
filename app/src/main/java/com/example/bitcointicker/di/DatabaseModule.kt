package com.example.bitcointicker.di

import android.content.Context
import androidx.room.Room
import com.example.bitcointicker.data.local.CoinDatabase
import com.example.bitcointicker.data.repository.DatabaseRepositoryImpl
import com.example.bitcointicker.domain.repository.DatabaseRepository
import com.example.bitcointicker.util.Constants.COIN_DATABASE
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): CoinDatabase {
        return Room.databaseBuilder(
            context,
            CoinDatabase::class.java,
            COIN_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(
        coinDatabase: CoinDatabase
    ): DatabaseRepository{
        return DatabaseRepositoryImpl(coinDatabase)
    }
}