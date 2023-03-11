package com.roseFinancials.lenafx.di

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
    fun provideYahooSearchApiService() = YahooSearchApiService.create()

    @Singleton
    @Provides
    fun provideYahooApiService() = YahooApiService.create()
}