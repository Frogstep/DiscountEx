package io.ilyasin.discountex.ui.details_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ilyasin.discountex.utils.DateTimeUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(): ViewModel(){

    companion object{
        private val TAG = DetailsViewModel::class.java.simpleName
    }

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _clockState = mutableStateOf(formatCurrentDateAndTime())
    val clockState : State<String> = _clockState

    init{
        uiScope.launch {
            Log.d(TAG, "Starting to update clock")
            while (isActive) {
                _clockState.value = formatCurrentDateAndTime()
                Log.d(TAG, "Updating clock: ${_clockState.value}")
                delay(60000)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "Stopping to update clock")
        viewModelJob.cancel()
    }

    private fun formatCurrentDateAndTime(): String {
        return DateTimeUtils.formatDate(System.currentTimeMillis())
    }
}