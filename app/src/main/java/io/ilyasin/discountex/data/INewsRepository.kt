package io.ilyasin.discountex.data

import io.ilyasin.discountex.data.schemas.ChannelData
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    suspend fun getFeeds(source: DataSource) : Flow<ChannelData>
}