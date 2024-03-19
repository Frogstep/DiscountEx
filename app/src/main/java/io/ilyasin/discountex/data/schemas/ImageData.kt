package io.ilyasin.discountex.data.schemas

import androidx.compose.runtime.Immutable

@Immutable
data class ImageData(val url: String, val width: Int, val height: Int, val type: String)