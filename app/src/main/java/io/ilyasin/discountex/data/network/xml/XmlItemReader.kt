package io.ilyasin.discountex.data.network.xml

import android.util.Log
import io.ilyasin.discountex.data.network.schemas.RssItem
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

object XmlItemReader {
    fun readItem(parser: XmlPullParser): RssItem {
        var eventType = parser.eventType
        val rssItem = RssItem()
        if (root(parser, "item")) {
            while (!(eventType == XmlPullParser.END_TAG && parser.name == "item")) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        val tagName = parser.name
                        when (tagName) {
                            "title" -> {
                                rssItem.title = parser.nextText()
                            }

                            "link" -> {
                                rssItem.link = parser.nextText()
                            }

                            "description" -> {
                                rssItem.description = parser.nextText()
                            }

                            "pubDate" -> {
                                rssItem.pubDate = parser.nextText()
                            }

                            "guid" -> {
                                //rssItem.guid = parser.nextText()
                            }

                            "group" -> {
                                if(parser.prefix == "media")
                                    rssItem.media.addAll(ImageParser.parse(parser))
                            }
                        }
                    }
                }
                eventType = parser.next()
            }
        }
        return rssItem
    }

    @Throws(IOException::class, XmlPullParserException::class)
    fun root(parser: XmlPullParser, rootName: String): Boolean {
        var eventType = parser.eventType
        var foundRoot = false
        var startTagFound = false
        while (!startTagFound && eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_DOCUMENT -> {}
                XmlPullParser.START_TAG -> {
                    startTagFound = true
                    if (parser.name == rootName) {
                        foundRoot = true
                    }
                }
            }
            eventType = parser.next()
        }
        return foundRoot
    }
}