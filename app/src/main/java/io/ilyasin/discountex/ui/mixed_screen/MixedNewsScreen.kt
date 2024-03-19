package io.ilyasin.discountex.ui.mixed_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.ilyasin.discountex.ui.common.GridScreenContent


@Composable
fun MixedNewsScreen(navController: NavController, viewModel: MixedNewsViewModel = hiltViewModel()) {
    GridScreenContent(navController = navController, viewModel.feed, viewModel.progressState)
}
