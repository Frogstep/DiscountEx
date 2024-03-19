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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.ilyasin.discountex.ui.common.Screen
import io.ilyasin.discountex.ui.details_screen.DetailsScreen
import io.ilyasin.discountex.ui.news_screen.NewsScreen
import io.ilyasin.discountex.ui.news_screen.WebViewScreen
import io.ilyasin.discountex.ui.theme.DiscountExTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiscountExTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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
        composable(route = Screen.DetailsScreen.route) {
            DetailsScreen(navController = navController)
        }
        composable(Screen.RssNewsScreen.route) {
            NewsScreen(navController = navController)
        }

        composable(
            route = "${Screen.WebViewScreen.route}/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) {
            navBackStackEntry?.arguments?.getString("url")?.let {
                WebViewScreen(it)
            }
        }
    }
}