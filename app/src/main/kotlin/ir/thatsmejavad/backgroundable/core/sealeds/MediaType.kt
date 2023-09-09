package ir.thatsmejavad.backgroundable.core.sealeds

sealed class MediaType(val type: String) {

    data object Photo : MediaType("Photo")
    data object Video : MediaType("Video")

    companion object {
        fun fromString(value: String): MediaType {
            return when (value) {
                Photo.type -> Photo
                Video.type -> Video
                else -> error("unknown value $value")
            }
        }
    }
}
