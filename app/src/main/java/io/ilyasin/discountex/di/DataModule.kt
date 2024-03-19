package io.ilyasin.logonex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ilyasin.discountex.data.INewsRepository
import io.ilyasin.discountex.data.NewsRepository
import io.ilyasin.discountex.data.RssDataSource
import io.ilyasin.discountex.data.network.ApiService
import javax.inject.Singleton

/**
 * Hilt module provides dependencies for injections.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideApi(): ApiService = ApiService.create()

    @Provides
    fun provideProductRepository(rssDataSource: RssDataSource):
            INewsRepository = NewsRepository(rssDataSource)

    @Provides
    fun provideNetworkDataSource(apiService: ApiService): RssDataSource = RssDataSource(apiService)
}