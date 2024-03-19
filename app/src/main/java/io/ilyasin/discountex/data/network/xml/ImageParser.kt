package io.ilyasin.discountex.data.network.xml

import io.ilyasin.discountex.data.network.schemas.ItemImage
import org.xmlpull.v1.XmlPullParser

object ImageParser{

    fun parse(xmlPullParser: XmlPullParser): List<ItemImage> {
        val images = mutableListOf<ItemImage>()
        var eventType = xmlPullParser.eventType
        while (!(eventType == XmlPullParser.END_TAG && xmlPullParser.name == "group")) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    val tagName = xmlPullParser.name
                    when (tagName) {
                        "content" -> {
                            if(xmlPullParser.prefix == "media")
                                images.add(readImage(xmlPullParser))
                        }
                    }
                }
            }
            eventType = xmlPullParser.next()
        }
        return images
    }

    private fun readImage(xmlPullParser: XmlPullParser): ItemImage {
        val url = xmlPullParser.getAttributeValue(null, "url")
        val type = xmlPullParser.getAttributeValue(null, "type")
        val width = xmlPullParser.getAttributeValue(null, "width")
        val height = xmlPullParser.getAttributeValue(null, "height")
        return ItemImage(url, Integer.parseInt(width), Integer.parseInt(height), type)
    }
}