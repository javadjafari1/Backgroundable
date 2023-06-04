package ir.thatsmejavad.backgroundable.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.thatsmejavad.backgroundable.core.MediaType

@Entity("medias")
data class MediaEntity(
    @PrimaryKey
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val alt: String,
    val type: MediaType,

    @ColumnInfo(index = true)
    val photographer: String,

    @ColumnInfo(name = "avg-color", index = true)
    val avgColor: String,

    @ColumnInfo("photographer-id")
    val photographerId: Int,

    @ColumnInfo("photographer-url")
    val photographerUrl: String,
)
