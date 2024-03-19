package io.ilyasin.discountex.data.network

import io.ilyasin.discountex.data.network.schemas.RssChannel
import io.ilyasin.discountex.data.network.xml.XmlCustomConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("rss/{path}")
    suspend fun feeds(@Path("path") path: String): RssChannel

    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://rss.cnn.com/")
            .addConverterFactory(XmlCustomConverterFactory())
            .client(client)
            .build()

        fun create(): ApiService = retrofit.create(ApiService::class.java)
    }
}