package io.ilyasin.discountex.ui.news_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.ilyasin.discountex.R
import io.ilyasin.discountex.ui.mixed_screen.MixedNewsScreen
import io.ilyasin.discountex.ui.theme.Dimens.padding
import io.ilyasin.discountex.ui.travel_screen.TravelScreen

@Composable
fun NewsScreen(navController: NavController) {
    NewsScreenContent(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreenContent(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .padding(padding),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Blue,
                ),
                title = {
                    Text(stringResource(R.string.cnn_news), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Start)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Gray)
                    }
                }
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White), contentAlignment = Alignment.Center
        ) {
            NewsScreenTabs(navController)
        }
    }
}

@Composable
fun NewsScreenTabs(navController: NavController) {
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }

    val tabs = listOf(stringResource(R.string.travel), stringResource(R.string.mixed))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("news_screen")
    ) {
        TabRow(selectedTabIndex = tabIndex,
            containerColor = Color.White, contentColor = Color.Blue,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    height = 4.dp,
                    color = Color.Blue.copy(alpha = 0.7f)
                )
            }) {
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