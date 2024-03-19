package io.ilyasin.discountex.data.schemas

import androidx.compose.runtime.Immutable
import io.ilyasin.discountex.data.DataSource

@Immutable
data class ChannelData(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val lastBuildDate: String,
    val items: List<ItemData>,
    val source: DataSource = DataSource.NoSource
)