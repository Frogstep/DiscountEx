package io.ilyasin.discountex.ui.common

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ilyasin.discountex.data.schemas.ChannelData
import io.ilyasin.discountex.data.DataSource
import io.ilyasin.discountex.data.schemas.ItemData
import io.ilyasin.discountex.domain.IFeedUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

abstract class GridViewModel(private val useCase: IFeedUseCase) : ViewModel() {

    companion object {
        private val TAG = GridViewModel::class.java.simpleName
    }

    private val _feed = mutableStateOf<List<ItemData>>(emptyList())
    val feed: State<List<ItemData>> = _feed

    private val updateTimes = HashMap<DataSource, String>()

    private var viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _progressState = mutableStateOf<ProgressState>(ProgressState.NotInitialized)
    val progressState: State<ProgressState> = _progressState


    init {
        startFeedUpdate()
    }

    private fun startFeedUpdate() {
        ioScope.launch {
            while (isActive) {
                getFeed()
                delay(TimeUnit.SECONDS.toMillis(5))
            }
        }
    }

    private fun getFeed() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                if (feed.value.isEmpty())
                    _progressState.value = ProgressState.Loading
            }

            try {
                useCase.getFeed().collect { channel ->
                    Log.d(TAG, "getFeed: ${channel.title}")
                    withContext(Dispatchers.Main) {
                        updateFeedIfNeeded(channel)
                        _progressState.value = ProgressState.Idle
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Failed to get feed. Sources: ${useCase.getSources().joinToString(", ") { it.path }}", e)
                _progressState.value = ProgressState.Error(e.message ?: "Unknown error")
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "Stopping to update feed")
        viewModelJob.cancel()
    }

    open fun updateFeedIfNeeded(channel: ChannelData) {
        if (channel.pubDate != updateTimes[channel.source]) {
            Log.d(TAG, "Feed [${channel.source}] is going to be updated")
            val updatedItems = _feed.value.toMutableList()
            updatedItems.removeAll { it.source == channel.source }
            updatedItems += channel.items
            updateTimes[channel.source] = channel.pubDate
            _feed.value =
                runPostItemProcessingTask(updatedItems)
        } else {
            Log.d(TAG, "Feed [${channel.source}] is already updated")
        }
    }

    open fun runPostItemProcessingTask(items: List<ItemData>): List<ItemData> {
        return items
    }
}