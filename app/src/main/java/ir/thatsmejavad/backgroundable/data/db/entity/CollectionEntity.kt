package ir.thatsmejavad.backgroundable.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(index = true)
    val title: String,
    val description: String? = null,
    val mediaCount: Int,
    val photosCount: Int,
    val videosCount: Int,
    val isPrivate: Boolean,
)
