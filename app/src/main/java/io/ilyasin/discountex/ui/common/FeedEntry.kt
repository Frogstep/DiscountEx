package io.ilyasin.discountex.ui.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedEntry(val title: String, val url: String) : Parcelable
