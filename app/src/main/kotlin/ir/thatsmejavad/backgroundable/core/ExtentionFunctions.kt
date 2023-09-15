package ir.thatsmejavad.backgroundable.core

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
import ir.thatsmejavad.backgroundable.common.ui.UiText
import ir.thatsmejavad.backgroundable.common.ui.UiText.StringResource
import ir.thatsmejavad.backgroundable.core.Constants.RATE_LIMIT_CODE
import kotlinx.serialization.SerializationException
import java.io.File
import java.io.FileOutputStream
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Date
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

fun Uri.setAsWallpaper(context: Context, onError: (Throwable) -> Unit) {
    runCatching {
        val intent = Intent(Intent.ACTION_ATTACH_DATA)
        intent.setDataAndType(this, "image/*")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.putExtra("mimeType", "image/*")
        intent.putExtra(Intent.EXTRA_STREAM, this)

        context.startActivity(intent)
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
    val imageTime = System.currentTimeMillis()
    val imageDate = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault()).format(Date(imageTime))
    val imageName = String.format(Locale.getDefault(), "${name}_%s.png", imageDate)

    val cachedPath = File(directory, "wallpaper").apply { mkdirs() }
    val imageFile = File(cachedPath, imageName)

    FileOutputStream(imageFile).use { out -> compress(Bitmap.CompressFormat.PNG, 100, out) }
    return imageFile
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

fun Uri.shareFileWithUri(
    context: Context,
    contentType: String,
    text: String = "",
    onFail: (Exception) -> Unit,
) {
    try {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = contentType
            putExtra(Intent.EXTRA_STREAM, this@shareFileWithUri)
            putExtra(Intent.EXTRA_TEXT, text)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        val shareTitle = context.getString(R.string.label_send_to)
        val shareIntent = Intent.createChooser(intent, shareTitle)
        context.startActivity(shareIntent)
    } catch (e: Exception) {
        onFail(e)
    }
}
