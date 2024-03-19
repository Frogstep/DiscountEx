package io.ilyasin.discountex.ui.details_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ilyasin.discountex.utils.DateTimeUtils
import io.ilyasin.discountex.utils.DateTimeUtils.getMillisecondsTillNextMinute
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
            while (isActive) {
                _clockState.value = formatCurrentDateAndTime()
                delay(getMillisecondsTillNextMinute())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun formatCurrentDateAndTime(): String {
        return DateTimeUtils.formatDate(System.currentTimeMillis())
    }
}