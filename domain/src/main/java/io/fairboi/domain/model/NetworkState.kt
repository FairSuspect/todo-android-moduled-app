package io.fairboi.domain.model

import androidx.annotation.StringRes
import io.fairboi.mytodoapp.domain.R

sealed class NetworkState {
    data object Updating : NetworkState()
    data object Success : NetworkState()
    sealed class Error(@StringRes val messageId: Int) : NetworkState() {
        data object NetworkError : Error(R.string.network_error)
        data object UnknownError : Error(R.string.unknown_error)
    }
}