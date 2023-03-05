package com.roseFinancials.lenafx.core.di

import com.roseFinancials.lenafx.network.TiingoSearchApiService
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
    fun provideTiingoApiSearchService(): TiingoSearchApiService {
        return TiingoSearchApiService.create()
    }
}