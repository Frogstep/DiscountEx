package io.ilyasin.discountex.ui.details_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.ilyasin.discountex.ui.common.Screen
import io.ilyasin.discountex.ui.theme.Dimens.cornerRadius
import io.ilyasin.discountex.ui.theme.Dimens.padding

@Composable
fun DetailsScreen(navController: NavController, viewModel: DetailsViewModel = hiltViewModel()) {
    DetailsScreenContent(navController, viewModel.clockState)
}

@Composable
fun DetailsScreenContent(navController: NavController, clockState: State<String>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(), colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            shape = RoundedCornerShape(cornerRadius)
        ) {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(fontSize = 24.sp, text = "My name is: Bill Gates")
                Spacer(modifier = Modifier.padding(5.dp))
                Text(clockState.value)

                Button(modifier = Modifier.padding(top = padding),
                    onClick = { navController.navigate(Screen.RssNewsScreen.route) }) {
                    Text("Show news")
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    val date = remember { mutableStateOf("12/04/2021 12:00:00") }
    DetailsScreenContent(rememberNavController(),  date)
}
