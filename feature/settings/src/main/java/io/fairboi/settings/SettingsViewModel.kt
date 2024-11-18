package io.fairboi.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fairboi.domain.model.Settings
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.domain.repositories.SettingsRepository
import io.fairboi.settings.di.modules.BackgroundDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "SettingsViewModel"

class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    @BackgroundDispatcher private val backgroundDispatcher: CoroutineDispatcher,

    ) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState.Initial)
    internal val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()


    init {
        loadSettings()
        Log.d(TAG, "settings loaded")
    }

    private fun loadSettings() {
        viewModelScope.launch(backgroundDispatcher) {
            settingsRepository
                .getSettings()
                .map {
                    SettingsUiState.AppSettingsState.Loaded(it)
                }
                .catch {
                    SettingsUiState.AppSettingsState.Error(it.message ?: "Unknown error")
                }
                .stateIn(
                    viewModelScope,
                    SharingStarted.Eagerly,
                    SettingsUiState.AppSettingsState.Loading,
                )
                .collect { settingsState ->
                    _uiState.update {
                        Log.d(TAG, "Loaded settings: $settingsState")
                        it.copy(
                            appSettingsState = settingsState
                        )
                    }
                }
        }
    }

    internal fun onSettingsChanged(settings: Settings) {
        viewModelScope.launch(backgroundDispatcher) {
            settingsRepository.saveSettings(settings)

        }
    }

    internal fun onThemeChanged(theme: ThemeSettings) {
        Log.d(TAG, "onThemeChanged: $theme")
        onSettingsChanged(
            uiState.value.appSettingsState.let {
                if (it is SettingsUiState.AppSettingsState.Loaded) {
                    it.settings.copy(theme = theme)
                } else {
                    Settings(theme = theme)
                }
            }
        )
    }


}