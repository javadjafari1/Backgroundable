package ir.thatsmejavad.backgroundable.screens.settings.themesetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import ir.thatsmejavad.backgroundable.core.sealeds.ThemeColor
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ThemeSettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
) : ViewModel() {
    val userPreferencesFlow = settingRepository.userPreferencesFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UserPreferences()
    )

    fun updateTheme(theme: Theme) {
        viewModelScope.launch {
            settingRepository.setThemeMode(theme)
        }
    }

    fun updateIsMaterialYouEnabled(isEnabled: Boolean) {
        viewModelScope.launch {
            settingRepository.setMaterialYouIsEnabled(isEnabled)
        }
    }

    fun updateThemeColor(color: ThemeColor) {
        viewModelScope.launch {
            settingRepository.setThemeColor(color)
        }
    }
}
