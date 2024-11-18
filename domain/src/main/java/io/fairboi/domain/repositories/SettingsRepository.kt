package io.fairboi.domain.repositories

import io.fairboi.domain.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SettingsRepository {
    suspend fun saveSettings(setting: Settings)
     fun getSettings(): StateFlow<Settings>
}