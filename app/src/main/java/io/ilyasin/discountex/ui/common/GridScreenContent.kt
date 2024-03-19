package io.ilyasin.discountex.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.navigation.NavController
import io.ilyasin.discountex.data.ItemData
import io.ilyasin.discountex.ui.theme.Dimens
import java.net.URLEncoder


@Composable
fun GridScreenContent(navController: NavController, channel: State<List<ItemData>>, progressState: State<ProgressState>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        when (progressState.value) {
            ProgressState.Loading, ProgressState.NotInitialized -> LoadingView()
            ProgressState.Idle, ProgressState.Updating -> FeedsList(navController, channel)
            is ProgressState.Error -> ErrorView(navController, channel)
        }
    }
}

@Composable
fun ErrorView(navController: NavController, channel: State<List<ItemData>>) {
    if (channel.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp), contentAlignment = Alignment.Center
        ) {
            ErrorText("Unable to download RSS feed")
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
                    .padding(Dimens.padding), contentAlignment = Alignment.Center
            ) {
                ErrorText("Unable to update RSS feed")
            }
            FeedsList(navController, channel)
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
            .background(Color.Red, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .padding(Dimens.padding)
    )
}


@Composable
fun FeedsList(navController: NavController, channel: State<List<ItemData>>) {
    val listState = rememberLazyGridState()
    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
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
