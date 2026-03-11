package com.orcamentoevendas.di

import android.content.Context
import androidx.room.Room
import com.orcamentoevendas.data.local.database.AppDatabase
import com.orcamentoevendas.data.local.dao.OrcamentoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {

        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "orcamento_db"
        ).build()
    }

    @Provides
    fun provideDao(database: AppDatabase): OrcamentoDao {
        return database.orcamentoDao()
    }
}