package io.ilyasin.discountex.data.network.schemas

import io.ilyasin.discountex.data.ChannelData
import io.ilyasin.discountex.data.DataSource

/**
 * <channel>
 * 		<title>
 * 			<![CDATA[ CNN.com - RSS Channel - App Travel Section ]]>
 * 		</title>
 * 		<description>
 * 			<![CDATA[ CNN.com delivers up-to-the-minute news and information on the latest top stories, weather, entertainment, politics and more. ]]>
 * 		</description>
 * 		<link>https://www.cnn.com/app-travel-section/index.html</link>
 * 		<image>
 * 			<url>http://i2.cdn.turner.com/cnn/2015/images/09/24/cnn.digital.png</url>
 * 			<title>CNN.com - RSS Channel - App Travel Section</title>
 * 			<link>https://www.cnn.com/app-travel-section/index.html</link>
 * 		</image>
 * 		<generator>coredev-bumblebee</generator>
 * 		<lastBuildDate>Sun, 17 Mar 2024 12:51:28 GMT</lastBuildDate>
 * 		<pubDate>Fri, 21 Oct 2022 08:46:29 GMT</pubDate>
 * 		<copyright>
 * 			<![CDATA[ Copyright (c) 2024 Turner Broadcasting System, Inc. All Rights Reserved. ]]>
 * 		</copyright>
 * 		<language>
 * 			<![CDATA[ en-US ]]>
 * 		</language>
 * 		<ttl>10</ttl>
 */
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