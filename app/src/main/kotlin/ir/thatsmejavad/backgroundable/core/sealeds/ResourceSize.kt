package ir.thatsmejavad.backgroundable.core.sealeds

sealed class ResourceSize(val size: String) {
    data object Original : ResourceSize("original")
    data object Large2x : ResourceSize("large2x")
    data object Large : ResourceSize("large")
    data object Medium : ResourceSize("medium")
    data object Small : ResourceSize("small")
    data object Portrait : ResourceSize("portrait"), OrientationMode
    data object Landscape : ResourceSize("landscape"), OrientationMode
    data object Tiny : ResourceSize("tiny")

    companion object {
        fun fromString(size: String): ResourceSize {
            return when (size) {
                Original.size -> Original
                Large2x.size -> Large2x
                Large.size -> Large
                Medium.size -> Medium
                Small.size -> Small
                Portrait.size -> Portrait
                Landscape.size -> Landscape
                Tiny.size -> Tiny
                else -> error("unknown size: $size")
            }
        }
    }
}
