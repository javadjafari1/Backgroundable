package ir.thatsmejavad.backgroundable.core

import androidx.compose.ui.graphics.Color
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.common.ui.UiText
import ir.thatsmejavad.backgroundable.common.ui.UiText.StringResource
import ir.thatsmejavad.backgroundable.core.Constants.RATE_LIMIT_CODE
import kotlinx.serialization.SerializationException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.URL
import java.net.UnknownHostException
import java.util.Locale

fun Throwable?.getErrorMessage(): UiText {
    return when (this) {
        is ServerException -> {
            if (code == RATE_LIMIT_CODE) {
                StringResource(R.string.rate_limit_error_message)
            } else {
                if (message != null) {
                    UiText.DynamicString(message!!)
                } else {
                    StringResource(R.string.unexpected_error_message)
                }
            }
        }

        is SerializationException -> StringResource(R.string.serialization_error_message)
        is SocketTimeoutException -> StringResource(R.string.timeout_error_message)
        is UnknownHostException, is ConnectException -> StringResource(R.string.label_cant_connect_to_server)
        else -> StringResource(R.string.unexpected_error_message)
    }
}

fun Throwable.getSnackbarMessage(): SnackbarMessage {
    return SnackbarMessage(getErrorMessage())
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

fun String.capitalizeFirstChar(): String {
    return replaceFirstChar { firstChar ->
        if (firstChar.isLowerCase()) {
            firstChar.titlecase(Locale.getDefault())
        } else {
            firstChar.toString()
        }
    }
}

fun String.convertToRelativePath(): String? {
    return runCatching {
        val url = URL(this)
        var path = url.path
        val query = url.query

        if (query != null && query.isNotEmpty()) {
            path += "?$query"
        }

        path
    }.getOrNull()
}
