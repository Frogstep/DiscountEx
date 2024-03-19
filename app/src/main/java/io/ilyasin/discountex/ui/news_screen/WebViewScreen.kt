package io.ilyasin.discountex.ui.news_screen

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import io.ilyasin.discountex.R
import io.ilyasin.discountex.ui.common.ErrorText
import io.ilyasin.discountex.ui.common.LoadingView
import io.ilyasin.discountex.ui.common.ProgressState
import java.net.URLDecoder

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String) {

    val loadingState = remember {
        mutableStateOf<ProgressState>(ProgressState.Loading)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()

                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.setSupportZoom(true)
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                            loadingState.value = ProgressState.Idle
                        }

                        override fun onReceivedError(
                            view: WebView?,
                            request: WebResourceRequest?,
                            error: WebResourceError?
                        ) {
                            super.onReceivedError(view, request, error)
                            loadingState.value = ProgressState.Error(error.toString())
                        }
                    }
                }
            },
            update = { webView ->
                webView.loadUrl(URLDecoder.decode(url, "utf-8"))
            }
        )
        when (loadingState.value) {
            ProgressState.Loading -> LoadingView()
            is ProgressState.Error -> ErrorText(stringResource(R.string.failed_to_load_page))
            else -> {}
        }
    }
}