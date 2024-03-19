package io.ilyasin.discountex.ui.common

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.ilyasin.discountex.R
import io.ilyasin.discountex.data.ItemData
import io.ilyasin.discountex.data.DataSource
import io.ilyasin.discountex.data.ImageData
import io.ilyasin.discountex.ui.theme.Dimens.cornerRadius
import io.ilyasin.discountex.ui.theme.Dimens.itemHeight
import io.ilyasin.discountex.ui.theme.Dimens.smallCornerRadius
import io.ilyasin.discountex.utils.getGridImage

@Composable
fun FeedItem(item: ItemData, onItemClicked: (ItemData) -> Unit) {

    val frameColor = when (item.source) {
        DataSource.Travel -> Color.Yellow.copy(alpha = 0.5f)
        DataSource.Sport -> Color.Blue.copy(alpha = 0.5f)
        DataSource.Entertainment -> Color.Green.copy(alpha = 0.5f)
        else -> Color.Black.copy(alpha = 0.5f)
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = frameColor,
        ),
        shape = RoundedCornerShape(cornerRadius),
        modifier = Modifier
            .clickable { onItemClicked(item) }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .height(itemHeight)
                .padding(4.dp)
        ) {
            val (imageRef, title) = createRefs()
            val image = item.getGridImage()
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image?.url ?: "")
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.news_image),
                contentScale = ContentScale.FillBounds,
                error = painterResource(R.drawable.ic_broken_image),
                modifier = Modifier
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .clip(RoundedCornerShape(smallCornerRadius))
                    .fillMaxSize()
            )

            Text(text = item.title,
                color = Color.White,
                maxLines = 2,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .constrainAs(title) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(5.dp)

            )
        }
    }
}

@Preview
@Composable
fun FeedItemPreview() {
    FeedItem(
        item = ItemData(
            title = "This is some title",
            link = "https://www.google.com",
            description = "",
            pubDate = "",
            media = listOf(ImageData("https://i.pravatar.cc/300", 300, 300, "")),
            source = DataSource.Travel
        )
    ) {}
}
