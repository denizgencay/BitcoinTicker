package com.example.bitcointicker.di

import com.example.bitcointicker.data.remote.CoinGeckoApi
import com.example.bitcointicker.data.repository.RemoteDataRepositoryImpl
import com.example.bitcointicker.domain.model.Coin
import com.example.bitcointicker.domain.repository.RemoteDataRepository
import com.example.bitcointicker.util.Constants.BASE_URL
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinGeckoApi(retrofit: Retrofit): CoinGeckoApi{
        return retrofit.create(CoinGeckoApi::class.java)
    }


    @Provides
    @Singleton
    fun provideRemoteDataRepository(
        coinGeckoApi: CoinGeckoApi,
        dispatcherProvider: DispatcherProvider
    ): RemoteDataRepository {
        return RemoteDataRepositoryImpl(coinGeckoApi,dispatcherProvider)
    }

}