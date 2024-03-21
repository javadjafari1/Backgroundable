package ir.thatsmejavad.backgroundable.core

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize
import javax.inject.Inject

class AndroidDownloader @Inject constructor(
    context: Context
) : Downloader {
    private val downloadManager = context.getSystemService(
        DownloadManager::class.java
    )

    override fun download(
        url: String,
        alt: String,
        photographer: String,
        size: ResourceSize
    ): Long {
        val fileName = "$alt by $photographer - ${size.size}.jpeg"

        val request =
            DownloadManager.Request(Uri.parse(url))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
                .setTitle(alt)
                .setMimeType("image/jpeg")
                .setDescription("by $photographer")

        return downloadManager.enqueue(request)
    }
}
