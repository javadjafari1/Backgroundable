package ir.thatsmejavad.backgroundable.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "collections", indices = [Index("id", unique = true)])
data class CollectionEntity(
    val id: String,
    @ColumnInfo(index = true)
    val title: String,
    val description: String? = null,
    val mediaCount: Int,
    val photosCount: Int,
    val videosCount: Int,
    val isPrivate: Boolean,
    // we should save orderId because server data is shuffled,and we should sort the data to fix jump in screen.
    @ColumnInfo("order-id")
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0
)
