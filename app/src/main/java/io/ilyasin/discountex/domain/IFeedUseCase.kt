package io.ilyasin.discountex.domain

import io.ilyasin.discountex.data.ChannelData
import io.ilyasin.discountex.data.DataSource
import kotlinx.coroutines.flow.Flow

interface IFeedUseCase {
    suspend fun getFeed(): Flow<ChannelData>

    fun getSources(): List<DataSource>
}