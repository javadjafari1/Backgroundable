package ir.thatsmejavad.backgroundable.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import ir.thatsmejavad.backgroundable.core.ResourceSize


@Entity(tableName = "resources", primaryKeys = ["size", "media-id"])
data class ResourceEntity(

    @ColumnInfo(name = "media-id")
    val mediaId: Int,
    val size: ResourceSize,
    val url: String,
)
