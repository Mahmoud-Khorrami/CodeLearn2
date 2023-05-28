package com.mahapp1397.codelearn2.di

import android.content.Context
import androidx.room.Room
import com.mahapp1397.codelearn2.database.ColdLearn2Database
import com.mahapp1397.codelearn2.repository.LocalDataSource
import com.mahapp1397.codelearn2.repository.LocalDataSourceImpl
import com.mahapp1397.codelearn2.utils.Constants.CODE_LEARN2_DATABASE
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
        @ApplicationContext context: Context): ColdLearn2Database
    {
        return Room.databaseBuilder(context,
                                    ColdLearn2Database::class.java,
                                    CODE_LEARN2_DATABASE).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(coldLearn2Database: ColdLearn2Database): LocalDataSource
    {
        return LocalDataSourceImpl(codeLearn2Database = coldLearn2Database)
    }
}