package io.ilyasin.discountex.data

import io.ilyasin.discountex.data.network.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RssDataSource @Inject constructor(private val api: ApiService) {

    fun getFeedData(source: DataSource) = flow {
        emit(api.feeds(source.path))
    }
}