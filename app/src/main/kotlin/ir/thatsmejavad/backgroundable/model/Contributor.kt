package ir.thatsmejavad.backgroundable.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Contributor(
    @StringRes val name: Int,
    @StringRes val position: Int,
    @DrawableRes val image: Int,
    val links: List<ContributorLink>
)

data class ContributorLink(
    @DrawableRes val icon: Int,
    val url: String,
    val name: String
)
