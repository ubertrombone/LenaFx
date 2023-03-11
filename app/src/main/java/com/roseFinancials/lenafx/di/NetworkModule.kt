package com.roseFinancials.lenafx.di

import com.roseFinancials.lenafx.network.TiingoSearchApiService
import com.roseFinancials.lenafx.network.YahooApiService
import com.roseFinancials.lenafx.network.YahooSearchApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideTiingoSearchApiService(): TiingoSearchApiService {
        return TiingoSearchApiService.create()
    }

    @Singleton
    @Provides
    fun provideYahooSearchApiService() = YahooSearchApiService.create()

    @Singleton
    @Provides
    fun provideYahooApiService() = YahooApiService.create()
}