package io.ilyasin.discountex.ui.news_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.ilyasin.discountex.R
import io.ilyasin.discountex.ui.mixed_screen.MixedNewsScreen
import io.ilyasin.discountex.ui.travel_screen.TravelScreen

@Composable
fun NewsScreen(navController: NavController) {
    NewsScreenContent(navController)
}

@Composable
fun NewsScreenContent(navController: NavController) {
    var tabIndex by rememberSaveable  { mutableIntStateOf(0) }

    val tabs = listOf(stringResource(R.string.travel), stringResource(R.string.mixed))

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> TravelScreen(navController = navController)
            1 -> MixedNewsScreen(navController = navController)
        }
    }
}