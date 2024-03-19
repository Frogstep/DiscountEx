package io.ilyasin.discountex.domain

import io.ilyasin.discountex.data.ChannelData
import io.ilyasin.discountex.data.DataSource
import io.ilyasin.discountex.data.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TravelUseCase @Inject constructor(private val newsRepository: INewsRepository): IFeedUseCase {
    override suspend fun getFeed() : Flow<ChannelData> {
        return newsRepository.getFeeds(DataSource.Travel)
    }

    override fun getSources(): List<DataSource> {
        return listOf(DataSource.Travel)
    }
}