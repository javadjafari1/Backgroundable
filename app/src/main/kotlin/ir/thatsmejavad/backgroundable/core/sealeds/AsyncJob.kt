package ir.thatsmejavad.backgroundable.core.sealeds

sealed class AsyncJob<out T>(private val value: T?) {
    data class Success<T>(val value: T) : AsyncJob<T>(value)
    data class Fail<T>(val exception: Exception) : AsyncJob<T>(null)
    data object Loading : AsyncJob<Nothing>(null)
    data object Uninitialized : AsyncJob<Nothing>(null)
}
