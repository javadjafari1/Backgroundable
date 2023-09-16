package ir.thatsmejavad.backgroundable.data.repository

import androidx.datastore.core.DataStore
import ir.thatsmejavad.backgroundable.UserPref
import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import ir.thatsmejavad.backgroundable.core.sealeds.ThemeColor
import ir.thatsmejavad.backgroundable.model.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val userPreferencesStore: DataStore<UserPref>
) : SettingRepository {

    override val userPreferencesFlow: Flow<UserPreferences> = userPreferencesStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(UserPref.getDefaultInstance())
            } else {
                throw exception
            }
        }.map {
            UserPreferences(
                isMaterialYouEnabled = it.isMaterialYou,
                theme = Theme.toTheme(it.theme),
                listType = List.toList(it.mediaColumnType),
                imageQuality = ImageQuality.toImageQuality(it.quality),
                themeColor = ThemeColor.toThemeColor(it.themeName)
            )
        }

    override suspend fun setMaterialYouIsEnabled(enable: Boolean) {
        userPreferencesStore.updateData { currentPref ->
            currentPref
                .toBuilder()
                .setIsMaterialYou(enable)
                .build()
        }
    }

    override suspend fun setThemeMode(theme: Theme) {
        userPreferencesStore.updateData { currentPref ->
            currentPref
                .toBuilder()
                .setTheme(Theme.fromTheme(theme))
                .build()
        }
    }

    override suspend fun setImageQuality(imageQuality: ImageQuality) {
        userPreferencesStore.updateData { currentPref ->
            currentPref
                .toBuilder()
                .setQuality(ImageQuality.fromImageQuality(imageQuality))
                .build()
        }
    }

    override suspend fun setThemeColor(color: ThemeColor) {
        userPreferencesStore.updateData { currentPref ->
            currentPref
                .toBuilder()
                .setThemeName(ThemeColor.fromThemeColor(color))
                .build()
        }
    }
}
