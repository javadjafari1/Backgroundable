package ir.thatsmejavad.backgroundable.core.sealeds

import ir.thatsmejavad.backgroundable.Quality

sealed interface ImageQuality {
    data object Low : ImageQuality
    data object Medium : ImageQuality
    data object High : ImageQuality
    data object Ultra : ImageQuality
    companion object {
        fun toImageQuality(quality: Quality): ImageQuality {
            return when (quality) {
                Quality.QUALITY_LOW -> Low
                Quality.QUALITY_MEDIUM -> Medium
                Quality.QUALITY_HIGH -> High
                Quality.UNRECOGNIZED -> Medium
                Quality.QUALITY_ULTRA -> Ultra
            }
        }

        fun fromImageQuality(imageQuality: ImageQuality): Quality {
            return when (imageQuality) {
                High -> Quality.QUALITY_HIGH
                Medium -> Quality.QUALITY_MEDIUM
                Low -> Quality.QUALITY_LOW
                Ultra -> Quality.QUALITY_ULTRA
            }
        }

        val items = listOf(Low, Medium, High, Ultra)

        fun ImageQuality.toResourceSize(): ResourceSize {
            return when (this) {
                High -> ResourceSize.Medium
                Medium -> ResourceSize.Small
                Low -> ResourceSize.Tiny
                Ultra -> ResourceSize.Original
            }
        }
    }
}
