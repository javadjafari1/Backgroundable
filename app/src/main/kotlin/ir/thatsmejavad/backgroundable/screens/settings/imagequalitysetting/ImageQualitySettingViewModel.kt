package ir.thatsmejavad.backgroundable.screens.settings.imagequalitysetting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.data.repository.SettingRepository
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageQualitySettingViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    val imageQuality = settingRepository.userPreferencesFlow
        .map { it.imageQuality }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            initialValue = UserPreferences().imageQuality
        )

    fun setImageQuality(quality: ImageQuality) {
        viewModelScope.launch {
            settingRepository.setImageQuality(quality)
        }
    }
}
