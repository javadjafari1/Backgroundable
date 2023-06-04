package ir.thatsmejavad.backgroundable.core

sealed class MediaType(val type: String) {

    object Photo : MediaType("Photo")
    object Video : MediaType("Video")

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
