package io.ilyasin.discountex.data.network.schemas

import io.ilyasin.discountex.data.ChannelData
import io.ilyasin.discountex.data.DataSource

data class RssChannel(
    var title: String? = null,
    var description: String? = null,
    var link: String? = null,
    var image: RssImage? = null,
    var lastBuildDate: String? = null,
    var pubDate: String? = null,
    val items: MutableList<RssItem> = mutableListOf()
)

fun RssChannel.toChannelData(source: DataSource): ChannelData {
    return ChannelData(title ?: "", link ?: "", description ?: "",
        pubDate ?: "", lastBuildDate ?: "", items.map { it.toItemData(source) }, source)
}

data class RssImage(val url: String? = null, val title: String? = null, val link: String? = null)