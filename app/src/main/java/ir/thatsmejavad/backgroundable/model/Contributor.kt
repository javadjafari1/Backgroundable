package ir.thatsmejavad.backgroundable.model

import androidx.annotation.DrawableRes

data class Contributor(
    val name: String,
    val position: String,
    @DrawableRes val image: Int,
    val links: List<ContributorLink>
)

data class ContributorLink(
    @DrawableRes val icon: Int,
    val url: String,
    val name: String
)
