package io.ilyasin.discountex.ui.common

/**
 * Navigation routes for the app
 */
sealed class Screen(val route: String) {
    data object DetailsScreen : Screen("Details")
    data object RssNewsScreen : Screen("RssNews")
    data object WebViewScreen : Screen("WebViewScreen")
}

const val PRODUCT_SCREEN_PARAMETER = "category"