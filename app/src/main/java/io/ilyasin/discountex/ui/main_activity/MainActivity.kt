package io.ilyasin.discountex.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.ilyasin.discountex.ui.common.FeedEntry
import io.ilyasin.discountex.ui.common.Screen
import io.ilyasin.discountex.ui.common.URL_PARAMETER
import io.ilyasin.discountex.ui.details_screen.DetailsScreen
import io.ilyasin.discountex.ui.news_screen.NewsScreen
import io.ilyasin.discountex.ui.news_screen.WebViewScreen
import io.ilyasin.discountex.ui.theme.DiscountExTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            DiscountExTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    NewsRssApp()
                }
            }
        }
    }
}

@Composable
fun NewsRssApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    NavHost(navController = navController, startDestination = Screen.DetailsScreen.route) {
        composable(route = Screen.DetailsScreen.route) { navBackResult ->
            var feedEntry: FeedEntry? = null
            navBackResult.savedStateHandle.get<FeedEntry>("feed")?.let {
                feedEntry = it
            }
            DetailsScreen(navController = navController, feedEntry = feedEntry)
        }
        composable(Screen.RssNewsScreen.route) { navBackResult ->
            NewsScreen(navController = navController)
        }

        composable(
            route = "${Screen.WebViewScreen.route}/{$URL_PARAMETER}",
            arguments = listOf(navArgument(URL_PARAMETER) { type = NavType.StringType })
        ) {
            navBackStackEntry?.arguments?.getString(URL_PARAMETER)?.let {
                WebViewScreen(it)
            }
        }
    }
}
