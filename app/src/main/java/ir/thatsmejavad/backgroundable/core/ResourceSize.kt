package ir.thatsmejavad.backgroundable.core

sealed class ResourceSize(val size: String) {
    object Original : ResourceSize("original")
    object Large2x : ResourceSize("large2x")
    object Large : ResourceSize("large")
    object Medium : ResourceSize("medium")
    object Small : ResourceSize("small")
    object Portrait : ResourceSize("portrait")
    object Landscape : ResourceSize("landscape")
    object Tiny : ResourceSize("tiny")

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