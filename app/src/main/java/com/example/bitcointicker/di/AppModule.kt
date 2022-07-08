package com.example.bitcointicker.di

import com.example.bitcointicker.util.dispatcher_provider.DispatcherProvider
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Singleton
    @Binds
    fun bindDispatcherProvider(impl: DispatcherProviderImpl?): DispatcherProvider?
}