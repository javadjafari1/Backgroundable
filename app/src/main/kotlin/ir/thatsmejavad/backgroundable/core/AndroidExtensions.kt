package ir.thatsmejavad.backgroundable.core

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.FileProvider
import ir.thatsmejavad.backgroundable.R
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Context.openUrl(url: String) {
    runCatching {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }.getOrElse {
        toast(R.string.label_no_app_found_to_handle_this_request)
    }
}

fun Context.composeMail(
    recipientMail: String,
    subject: String = "",
    message: String = "",
) {
    runCatching {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientMail))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, message)
            data = Uri.parse("mailto:")
        }
        startActivity(emailIntent)
    }.getOrElse {
        toast(R.string.label_no_app_found_to_handle_this_request)
    }
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(@StringRes stringRes: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, stringRes, duration).show()
}

fun Uri.shareFileWithUri(
    context: Context,
    contentType: String,
    text: String = "",
    onFail: (Throwable) -> Unit,
) {
    runCatching {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = contentType
            putExtra(Intent.EXTRA_STREAM, this@shareFileWithUri)
            putExtra(Intent.EXTRA_TEXT, text)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        val shareTitle = context.getString(R.string.label_send_to)
        val shareIntent = Intent.createChooser(intent, shareTitle)
        context.startActivity(shareIntent)
    }.getOrElse {
        onFail(it)
    }
}

fun File.getUri(context: Context): Uri {
    return FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        this
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
