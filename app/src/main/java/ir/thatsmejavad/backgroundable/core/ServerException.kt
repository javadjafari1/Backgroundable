package ir.thatsmejavad.backgroundable.core

data class ServerException(
    val code: Int,
    val error: String,
) : Exception()
