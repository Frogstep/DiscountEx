package io.ilyasin.discountex.data.network.xml

import io.ilyasin.discountex.data.network.schemas.RssChannel
import org.xmlpull.v1.XmlPullParser

object XmlChannelReader {

    fun readChannel(parser: XmlPullParser): RssChannel {
        var eventType = parser.eventType
        val rssChannel = RssChannel()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    val tagName = parser.name
                    when (tagName) {
                        "title" -> {
                            rssChannel.title = parser.nextText()
                        }
                        "link" -> {
                            rssChannel.link = parser.nextText()
                        }
                        "description" -> {
                            rssChannel.description = parser.nextText()
                        }
                        "pubDate" -> {
                            rssChannel.pubDate = parser.nextText()
                        }
                        "lastBuildDate" -> {
                            rssChannel.lastBuildDate = parser.nextText()
                        }
                        "item" -> {
                            rssChannel.items.add(XmlItemReader.readItem(parser))
                        }

                    }
                }
            }
            eventType = parser.next()
        }
        return rssChannel
    }
}