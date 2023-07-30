package ir.thatsmejavad.backgroundable.model

data class Contributor(
    val name: String,
    val position: String,
    val image: Int,
    val links: List<ContributorLink>
)

data class ContributorLink(
    val icon: Int,
    val url: String,
    val name: String
)
