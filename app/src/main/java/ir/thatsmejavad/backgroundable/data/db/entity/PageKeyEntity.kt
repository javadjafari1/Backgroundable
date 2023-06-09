package ir.thatsmejavad.backgroundable.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "page-key")
data class PageKeyEntity(
    @PrimaryKey
    @ColumnInfo(name = "collection-id")
    val collectionId: String,

    @ColumnInfo(name = "last-loaded-page")
    val lastLoadedPage: Int,

    @ColumnInfo(name = "max-page")
    val maxPage: Int,

    val timestamp: Long = System.currentTimeMillis()
)
