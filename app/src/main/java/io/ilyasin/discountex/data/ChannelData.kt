package io.ilyasin.discountex.data

import androidx.compose.runtime.Immutable

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