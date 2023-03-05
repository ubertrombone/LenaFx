package com.roseFinancials.lenafx.core.di

import com.roseFinancials.lenafx.data.repositories.EtfDataStateRepository
import com.roseFinancials.lenafx.data.repositories.StocksStateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideStocksStateRepository(): StocksStateRepository {
        return StocksStateRepository()
    }

    @Singleton
    @Provides
    fun provideEtfDataStateRepository(): EtfDataStateRepository {
        return EtfDataStateRepository()
    }
}