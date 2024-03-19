package io.ilyasin.discountex.data

import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    suspend fun getFeeds(source: DataSource) : Flow<ChannelData>
}