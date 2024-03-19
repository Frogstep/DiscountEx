package io.ilyasin.discountex.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import io.ilyasin.discountex.R
import io.ilyasin.discountex.data.schemas.ItemData
import io.ilyasin.discountex.ui.theme.Dimens.cornerRadius
import io.ilyasin.discountex.ui.theme.Dimens.itemMinWidth
import io.ilyasin.discountex.ui.theme.Dimens.largePadding
import io.ilyasin.discountex.ui.theme.Dimens.padding
import java.net.URLEncoder


@Composable
fun GridScreenContent(
    navController: NavController,
    channel: State<List<ItemData>>,
    progressState: State<ProgressState>,
    gridState: LazyGridState?,
    saveGridState: (LazyGridState) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()//.background(Color.White),
    ) {
        when (progressState.value) {
            ProgressState.Loading, ProgressState.NotInitialized -> LoadingView()
            ProgressState.Idle, ProgressState.Updating -> FeedsList(navController, channel, gridState, saveGridState)
            is ProgressState.Error -> ErrorView(navController, channel, gridState, saveGridState)
        }
    }
}

@Composable
fun ErrorView(
    navController: NavController,
    channel: State<List<ItemData>>,
    gridState: LazyGridState?,
    saveGridState: (LazyGridState) -> Unit
) {
    if (channel.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = largePadding), contentAlignment = Alignment.Center
        ) {
            ErrorText(stringResource(R.string.unable_to_download_rss_feed))
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding), contentAlignment = Alignment.Center
            ) {
                ErrorText(stringResource(R.string.unable_to_update_rss_feed))
            }
            FeedsList(navController, channel, gridState, saveGridState)
        }
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.Blue)
    }
}

@Composable
fun ErrorText(text: String) {
    Text(
        text = text,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(Color.Red, shape = RoundedCornerShape(cornerRadius))
            .fillMaxWidth()
            .padding(padding)
    )
}


@Composable
fun FeedsList(
    navController: NavController,
    channel: State<List<ItemData>>,
    gridState: LazyGridState?,
    saveGridState: (LazyGridState) -> Unit
) {
    val localGridState = gridState?: rememberLazyGridState()
    LaunchedEffect(localGridState) {
        saveGridState(localGridState)
    }
    //val listStateSaveAble = rememberSaveable { rememberLazyGridState() }
    //val listStateSaveAble = rememberSaveable { rememberLazyGridState() }
    LazyVerticalGrid(
        state = localGridState,
        columns = GridCells.Adaptive(minSize = itemMinWidth),
        modifier = Modifier.padding(top = padding, bottom = padding),
        verticalArrangement = Arrangement.spacedBy(padding),
        horizontalArrangement = Arrangement.spacedBy(padding)
    ) {
        items(channel.value.size, key = { index -> channel.value[index].link }) { index ->
            val item = channel.value[index]
            FeedItem(item = item, onItemClicked = {
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set("feed", FeedEntry(it.title, it.link))

                navController.navigate("${Screen.WebViewScreen}/${URLEncoder.encode(it.link, "utf-8")}")
            })
        }
    }
}
