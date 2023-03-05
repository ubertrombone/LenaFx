package com.roseFinancials.lenafx.di

import com.roseFinancials.lenafx.network.TiingoSearchApiService
import com.roseFinancials.lenafx.network.TiingoTickerApiService
import com.roseFinancials.lenafx.network.YahooApiService
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
    fun provideTiingoTickerApiService(): TiingoTickerApiService {
        return TiingoTickerApiService.create()
    }

    @Singleton
    @Provides
    fun provideYahooApiService(): YahooApiService {
        return YahooApiService.create()
    }
}