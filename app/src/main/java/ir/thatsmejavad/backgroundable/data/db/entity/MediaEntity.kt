package ir.thatsmejavad.backgroundable.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import ir.thatsmejavad.backgroundable.core.sealeds.MediaType

@Entity("medias", indices = [Index("id", unique = true)])
data class MediaEntity(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val alt: String,
    val type: MediaType,

    @ColumnInfo(name = "collection-id")
    val collectionId: String?,

    @ColumnInfo(index = true)
    val photographer: String,

    @ColumnInfo(name = "avg-color", index = true)
    val avgColor: String,

    @ColumnInfo("photographer-id")
    val photographerId: Int,

    @ColumnInfo("photographer-url")
    val photographerUrl: String,

    // we should save orderId because server data is shuffled,and we should sort the data to fix jump in screen.
    @ColumnInfo("order-id")
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0
)
