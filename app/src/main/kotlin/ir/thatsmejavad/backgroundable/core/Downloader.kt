package ir.thatsmejavad.backgroundable.core

import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize

interface Downloader {
    fun download(
        url: String,
        alt: String,
        photographer: String,
        size: ResourceSize
    ): Long
}
