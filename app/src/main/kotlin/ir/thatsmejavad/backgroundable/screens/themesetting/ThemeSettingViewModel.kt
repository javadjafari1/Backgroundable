package ir.thatsmejavad.backgroundable.screens.themesetting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeSettingViewModel @AssistedInject constructor(
    private val settingRepository: SettingRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<ThemeSettingViewModel>

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
}
