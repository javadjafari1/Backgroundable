package ir.thatsmejavad.backgroundable.model

import ir.thatsmejavad.backgroundable.core.sealeds.ImageQuality
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.sealeds.Theme
import ir.thatsmejavad.backgroundable.core.sealeds.ThemeColor

data class UserPreferences(
    val isMaterialYouEnabled: Boolean = false,
    val theme: Theme = Theme.FollowSystem,
    val listType: List = List.StaggeredType,
    val imageQuality: ImageQuality = ImageQuality.Medium,
    val themeColor: ThemeColor = ThemeColor.Skobeloff
)
