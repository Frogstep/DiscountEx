package io.ilyasin.discountex.ui.common

sealed class ProgressState {
    data object Loading : ProgressState()
    data object Idle : ProgressState()
    data object NotInitialized : ProgressState()
    data object Updating : ProgressState()
    data class  Error(val message: String) : ProgressState()
}