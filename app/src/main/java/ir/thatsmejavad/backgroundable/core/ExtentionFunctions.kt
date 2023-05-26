package ir.thatsmejavad.backgroundable.core

import android.content.Context
import androidx.compose.ui.graphics.Color
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.Constants.RATE_LIMIT_CODE
import kotlinx.serialization.SerializationException
import java.net.SocketTimeoutException

fun Throwable.getErrorMessage(): Any {
    return when (this) {
        is ServerException -> {
            if (code == RATE_LIMIT_CODE) {
                R.string.rate_limit_error_message
            } else {
                message ?: R.string.unexpected_error_message
            }
        }

        is SerializationException -> R.string.serialization_error_message
        is SocketTimeoutException -> R.string.timeout_error_message
        else -> R.string.unexpected_error_message
    }
}

fun Throwable.getStringMessage(context: Context): String {
    return when (val message = getErrorMessage()) {
        is String -> message
        is Int -> context.getString(message)
        else -> context.getString(R.string.unexpected_error_message)
    }
}

fun Throwable.getSnackbarMessage(): SnackbarMessage {
    return SnackbarMessage(
        message = getErrorMessage()
    )
}

fun String.toColor(): Color {
    val hexWithoutSymbol = removePrefix("#")

    val redHex = hexWithoutSymbol.substring(0, 2)
    val greenHex = hexWithoutSymbol.substring(2, 4)
    val blueHex = hexWithoutSymbol.substring(4, 6)

    val red = redHex.toInt(16)
    val green = greenHex.toInt(16)
    val blue = blueHex.toInt(16)

    return Color(
        red = red / 255f,
        green = green / 255f,
        blue = blue / 255f,
    )
}
