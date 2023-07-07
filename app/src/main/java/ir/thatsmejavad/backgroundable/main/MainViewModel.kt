package ir.thatsmejavad.backgroundable.main

import androidx.lifecycle.ViewModel
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    settingRepository: SettingRepository,
) : ViewModel() {

    val userPreferences = settingRepository.userPreferencesFlow
}
