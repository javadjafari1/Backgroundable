package ir.thatsmejavad.backgroundable.core

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.core.content.FileProvider
import ir.thatsmejavad.backgroundable.R
import ir.thatsmejavad.backgroundable.core.Constants.RATE_LIMIT_CODE
import kotlinx.serialization.SerializationException
import java.io.File
import java.io.FileOutputStream
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Locale

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
        is UnknownHostException -> R.string.label_cant_connect_to_server
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

fun Activity.setWallpaperWithImage(uri: Uri, onError: (Throwable) -> Unit) {
    runCatching {
        val intent = Intent(Intent.ACTION_ATTACH_DATA)
        intent.setDataAndType(uri, "image/*")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.putExtra("mimeType", "image/*")
        intent.putExtra(Intent.EXTRA_STREAM, uri)

        startActivity(intent)
    }.getOrElse {
        onError(it)
    }
}

fun File.getUri(context: Context): Uri {
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        this
    )
}

fun Bitmap.saveIn(
    directory: File,
    name: String = "image"
): File {
    val tempFile = File.createTempFile(name, ".jpg", directory)
    FileOutputStream(tempFile).use {
        compress(Bitmap.CompressFormat.JPEG, 100, it)
    }
    return tempFile
}

fun Context.openUrl(url: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } catch (e: ActivityNotFoundException) {
        toast(R.string.label_no_app_found_to_handle_this_request)
    }
}

fun Context.composeMail(
    recipientMail: String,
    subject: String = "",
    message: String = "",
) {
    try {
        val selectorIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
        }
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientMail))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
            selector = selectorIntent
        }
        startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        toast(R.string.label_no_app_found_to_handle_this_request)
    }
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(@StringRes stringRes: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, stringRes, duration).show()
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
