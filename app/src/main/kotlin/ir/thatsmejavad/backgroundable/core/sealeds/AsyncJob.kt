package ir.thatsmejavad.backgroundable.core.sealeds

sealed interface AsyncJob<out T> {
    data class Success<T>(val value: T) : AsyncJob<T>
    data class Fail<T>(val exception: Throwable) : AsyncJob<T>
    data object Loading : AsyncJob<Nothing>
    data object Uninitialized : AsyncJob<Nothing>
}
