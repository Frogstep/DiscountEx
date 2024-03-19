package io.ilyasin.discountex.data

import io.ilyasin.discountex.data.network.schemas.toChannelData
import io.ilyasin.discountex.data.schemas.ChannelData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(private val rssDataSource: RssDataSource) : INewsRepository {
    override suspend fun getFeeds(source: DataSource) : Flow<ChannelData> {
        return rssDataSource.getFeedData(source).map { it.toChannelData(source) }
    }
}