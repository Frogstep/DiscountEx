package io.ilyasin.discountex.data.network.xml

import okhttp3.ResponseBody
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import retrofit2.Converter
import java.io.StringReader

class XmlResponseBodyConverter<T> : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T {
        val xmlPullParser = createXmlPullParser(value.string())
        return XmlChannelReader.readChannel(xmlPullParser) as T
    }

    private fun createXmlPullParser(xmlString: String): XmlPullParser {
        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val parser = factory.newPullParser()
        parser.setInput(StringReader(xmlString))
        return parser
    }


}