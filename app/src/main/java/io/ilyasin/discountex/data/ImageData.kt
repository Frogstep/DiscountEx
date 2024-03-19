package io.ilyasin.discountex.data

import androidx.compose.runtime.Immutable

@Immutable
data class ImageData(val url: String, val width: Int, val height: Int, val type: String)