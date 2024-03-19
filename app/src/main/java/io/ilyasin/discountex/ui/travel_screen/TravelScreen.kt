package io.ilyasin.discountex.ui.travel_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.ilyasin.discountex.ui.common.GridScreenContent

@Composable
fun TravelScreen(navController: NavController, viewModel: TravelViewModel = hiltViewModel()) {
    GridScreenContent(navController = navController, viewModel.feed, viewModel.progressState)
}