package ir.thatsmejavad.backgroundable.data.repository

import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    val userPreferencesFlow: Flow<UserPreferences>
    suspend fun setThemeMode(theme: Theme)
    suspend fun setMaterialYouIsEnabled(enable: Boolean)
}
