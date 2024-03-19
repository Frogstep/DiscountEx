package io.ilyasin.discountex.utils

import io.ilyasin.discountex.data.ImageData
import io.ilyasin.discountex.data.ItemData


fun ItemData.getGridImage(): ImageData? {
    var squareImage: ImageData? = null
    media.forEach { image ->
        if (image.type == "image/jpeg" && image.height == image.width) {
            squareImage = getSmallerImage(image, squareImage)
        }
    }
    return squareImage
}


fun getSmallerImage(image1: ImageData, image2: ImageData?): ImageData {
    return image2?.let {
        if (image1.width + image1.height < it.width + it.height) image1 else it
    } ?: image1
}
