package io.ilyasin.discountex.data

sealed class DataSource(val path: String) {
    data object NoSource : DataSource("")
    data object Travel : DataSource("cnn_travel.rss")
    data object Sport : DataSource("edition_sport.rss")
    data object Entertainment : DataSource("edition_entertainment.rss")
}