package io.fairboi.utils

import io.fairboi.domain.model.Settings
import io.fairboi.domain.model.ThemeSettings
import io.fairboi.domain.repositories.SettingsRepository
import io.fairboi.domain.utils.KeyValueStorage
import io.fairboi.utils.di.modules.AppSettingsQualifier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    @AppSettingsQualifier private val saver: KeyValueStorage,
    @AppSettingsQualifier private val coroutineDispatcher: CoroutineDispatcher,
) : SettingsRepository {
    private val _state = MutableStateFlow(Settings())

    init {
        CoroutineScope(coroutineDispatcher).launch {
            _state.update { state ->
                saver.get(SETTINGS_KEY)
                    ?.let { Json.decodeFromString<SettingsDto>(it) }
                    ?.toSettings() ?: state
            }
        }
    }

    override suspend fun saveSettings(settings: Settings) = withContext(coroutineDispatcher) {
        _state.update { settings }
        val serializer = serializer<SettingsDto>()
        saver.save(SETTINGS_KEY, Json.encodeToString(
            serializer,
            settings.toDto()))
    }

    override  fun getSettings(): StateFlow<Settings> = _state.asStateFlow()

    companion object {
        const val SETTINGS_KEY = "settings"
    }
}

private fun Settings.toDto() = SettingsDto(
    theme = theme.name,
)

private fun SettingsDto.toSettings() = Settings(
    ThemeSettings.valueOf(theme),
)

@Serializable
private data class SettingsDto(
    val theme: String,
)
