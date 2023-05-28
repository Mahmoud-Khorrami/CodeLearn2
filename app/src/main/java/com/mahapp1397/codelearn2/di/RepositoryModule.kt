package com.mahapp1397.codelearn2.di

import android.content.Context
import com.mahapp1397.codelearn2.repository.DataStoreOperations
import com.mahapp1397.codelearn2.repository.DataStoreOperationsImpl
import com.mahapp1397.codelearn2.repository.Repository
import com.mahapp1397.codelearn2.use_cases.GetPhoneNumberUseCase
import com.mahapp1397.codelearn2.use_cases.GetProfilesUseCase
import com.mahapp1397.codelearn2.use_cases.GetSelectedProfileUseCase
import com.mahapp1397.codelearn2.use_cases.SavePhoneNumberUseCase
import com.mahapp1397.codelearn2.use_cases.SaveProfileUseCase
import com.mahapp1397.codelearn2.use_cases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule
{
    @Provides
    @Singleton
    fun provideDataStoreOperation(@ApplicationContext context: Context): DataStoreOperations
    {
        return DataStoreOperationsImpl(context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases
    {
        return UseCases(
            SavePhoneNumberUseCase(repository),
            GetPhoneNumberUseCase(repository),
            SaveProfileUseCase(repository),
            GetProfilesUseCase(repository),
            GetSelectedProfileUseCase(repository)
            )
    }
}