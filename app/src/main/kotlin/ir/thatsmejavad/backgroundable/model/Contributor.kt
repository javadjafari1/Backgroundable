package ir.thatsmejavad.backgroundable.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Contributor(
    @param:StringRes val name: Int,
    @param:StringRes val position: Int,
    @param:DrawableRes val image: Int,
    val links: List<ContributorLink>
)

data class ContributorLink(
    @param:DrawableRes val icon: Int,
    val url: String,
    val name: String
)
