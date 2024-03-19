package io.ilyasin.discountex.data

import androidx.compose.runtime.Immutable

@Immutable
data class ItemData(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val media: List<ImageData>,
    val source: DataSource = DataSource.NoSource
)