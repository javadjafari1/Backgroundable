package ir.thatsmejavad.backgroundable.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize

@Entity(
    tableName = "resources",
    primaryKeys = ["size", "media-id"],
    indices = [Index("media-id")],
    foreignKeys = [
        ForeignKey(
            entity = MediaEntity::class,
            parentColumns = ["id"],
            childColumns = ["media-id"],
            onDelete = ForeignKey.CASCADE,
        )
    ]
)
data class ResourceEntity(
    @ColumnInfo(name = "media-id")
    val mediaId: Int,
    val size: ResourceSize,
    val url: String,
)
