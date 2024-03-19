package io.ilyasin.discountex.domain

import io.ilyasin.discountex.data.ChannelData
import io.ilyasin.discountex.data.INewsRepository
import io.ilyasin.discountex.data.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class MixedFeedUseCase @Inject constructor(private val newsRepository: INewsRepository) : IFeedUseCase {

    override suspend fun getFeed(): Flow<ChannelData> {
        return merge(newsRepository.getFeeds(DataSource.Sport), newsRepository.getFeeds(DataSource.Entertainment))
    }

    override fun getSources(): List<DataSource> {
        return listOf(DataSource.Sport, DataSource.Entertainment)
    }
}