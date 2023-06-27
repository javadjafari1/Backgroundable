package ir.thatsmejavad.backgroundable.model

import ir.thatsmejavad.backgroundable.core.sealeds.Theme

data class UserPreferences(
    val isMaterialYouEnabled: Boolean,
    val theme: Theme,
)
