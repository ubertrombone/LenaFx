package com.roseFinancials.lenafx.di

import com.roseFinancials.lenafx.data.repositories.ApiDataRepository
import com.roseFinancials.lenafx.data.repositories.EtfDataStateRepository
import com.roseFinancials.lenafx.data.repositories.StocksStateRepository
import com.roseFinancials.lenafx.network.YahooApiService
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

    @Singleton
    @Provides
    fun provideApiDataRepository(yahooApiService: YahooApiService): ApiDataRepository {
        return ApiDataRepository(yahooApiService)
    }
}