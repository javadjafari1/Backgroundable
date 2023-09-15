package ir.thatsmejavad.backgroundable.screens.imagequalitysetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageQualitySettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    val userPreferences = settingRepository.userPreferencesFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        initialValue = UserPreferences()
    )

    fun setImageQuality(quality: ImageQuality) {
        viewModelScope.launch {
            settingRepository.setImageQuality(quality)
        }
    }
}
