package io.ilyasin.discountex.data.schemas

import androidx.compose.runtime.Immutable
import io.ilyasin.discountex.data.DataSource

@Immutable
data class ItemData(
    val title: String,
    val link: String,
    val description: String,
    val pubDate: String,
    val media: List<ImageData>,
    val source: DataSource = DataSource.NoSource
)