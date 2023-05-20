package ir.thatsmejavad.backgroundable.core

import ir.thatsmejavad.backgroundable.R
import java.net.SocketTimeoutException

fun Throwable.getSnackbarMessage(): SnackbarMessage {
    val message: Any = when (this) {
        is ServerException -> {
            if (code == 429) {
                R.string.rate_limit_error_message
            } else {
                message ?: R.string.unexpected_error_message
            }
        }

        is SocketTimeoutException -> R.string.timeout_error_message
        else -> R.string.unexpected_error_message
    }
    return SnackbarMessage(
        message = message
    )
}
