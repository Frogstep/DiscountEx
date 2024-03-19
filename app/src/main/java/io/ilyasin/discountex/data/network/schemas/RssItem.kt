package io.ilyasin.discountex.data.network.schemas

import io.ilyasin.discountex.data.DataSource
import io.ilyasin.discountex.data.schemas.ImageData
import io.ilyasin.discountex.data.schemas.ItemData

data class RssItem(
    var title: String? = null,
    var description: String? = null,
    var link: String? = null,
    //val guid: String ,
    var pubDate: String? = null,
    var media: MutableList<ItemImage> = mutableListOf()
)

data class ItemImage(val url: String, val width: Int, val height: Int, val type: String)

fun ItemImage.toImageData(): ImageData {
    return ImageData(url, width, height, type)
}

fun RssItem.toItemData(source: DataSource): ItemData {
    return ItemData(title ?: "", link ?: "", description ?: "", pubDate ?: "", media.map { it.toImageData() }, source)
}
