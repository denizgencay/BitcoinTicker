package com.example.bitcointicker.di

import com.example.bitcointicker.data.repository.FirebaseRepositoryImpl
import com.example.bitcointicker.domain.repository.FirebaseRepository
import com.example.bitcointicker.util.dispatcher_provider.DispatcherProvider
import com.example.bitcointicker.util.toast_message_helper.ToastMessageHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFireBaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseRepository(
        firebaseAuth: FirebaseAuth,
        firebaseFirestore: FirebaseFirestore,
        toastMessageHelper: ToastMessageHelper,
        dispatcherProvider: DispatcherProvider
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseAuth, firebaseFirestore, toastMessageHelper, dispatcherProvider)
    }
}