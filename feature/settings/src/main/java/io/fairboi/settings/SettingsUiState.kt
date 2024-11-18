package io.fairboi.settings

import io.fairboi.domain.model.Settings

internal data class SettingsUiState(
    val appSettingsState: AppSettingsState,
) {

    sealed class AppSettingsState {
        data object Loading : AppSettingsState()
        data class Loaded(val settings: Settings) : AppSettingsState()
        data class Error(val message: String) : AppSettingsState()
    }

    companion object {
        val Initial = SettingsUiState(AppSettingsState.Loading)
    }

}