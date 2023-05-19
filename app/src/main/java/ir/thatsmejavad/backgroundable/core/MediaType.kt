package ir.thatsmejavad.backgroundable.core

sealed class MediaType(val type: String) {

    object Photo : MediaType("Photo")
    object Video : MediaType("Video")
}
