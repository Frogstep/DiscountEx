package io.ilyasin.discountex.ui.travel_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.ilyasin.discountex.data.ItemData
import io.ilyasin.discountex.ui.common.FeedItem
import io.ilyasin.discountex.ui.common.GridScreenContent
import io.ilyasin.discountex.ui.common.ProgressState
import io.ilyasin.discountex.ui.common.Screen
import io.ilyasin.discountex.ui.theme.Dimens.padding
import java.net.URLEncoder

@Composable
fun TravelScreen(navController: NavController, viewModel: TravelViewModel = hiltViewModel()) {
    GridScreenContent(navController = navController, viewModel.feed, viewModel.progressState)
}