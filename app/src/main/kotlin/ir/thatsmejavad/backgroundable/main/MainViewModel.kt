package ir.thatsmejavad.backgroundable.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainViewModel @Inject constructor(
    settingRepository: SettingRepository,
) : ViewModel() {
    val userPreferences = settingRepository.userPreferencesFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = UserPreferences()
    )
}
