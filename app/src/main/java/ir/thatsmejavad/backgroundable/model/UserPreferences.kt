package ir.thatsmejavad.backgroundable.model

import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.core.sealeds.Theme

data class UserPreferences(
    val isMaterialYouEnabled: Boolean = false,
    val theme: Theme = Theme.FollowSystem,
    val listType: List = List.StaggeredType,
)
