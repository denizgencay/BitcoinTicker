package com.example.bitcointicker.di

import android.content.Context
import com.example.bitcointicker.util.toast_message_helper.ToastMessageHelper
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProvider
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProviderImpl
import com.example.bitcointicker.util.toast_message_helper.ToastMessageHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherProvider{
        return DispatcherProviderImpl()
    }

    @Singleton
    @Provides
    fun provideToastMessageHelper(
        @ApplicationContext context: Context
    ): ToastMessageHelper{
        return ToastMessageHelperImpl(context)
    }

}