package ir.thatsmejavad.backgroundable.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.thatsmejavad.backgroundable.data.db.converter.MediaTypeConverter
import ir.thatsmejavad.backgroundable.data.db.converter.ResourceSizeConverter
import ir.thatsmejavad.backgroundable.data.db.dao.CollectionDao
import ir.thatsmejavad.backgroundable.data.db.dao.MediaDao
import ir.thatsmejavad.backgroundable.data.db.dao.ResourceDao
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity

@Database(
    entities = [
        CollectionEntity::class,
        MediaEntity::class,
        ResourceEntity::class
    ],
    version = 1
)
@TypeConverters(MediaTypeConverter::class, ResourceSizeConverter::class)
abstract class BackgroundableDatabase : RoomDatabase() {
    abstract fun resourceDao(): ResourceDao
    abstract fun collectionDao(): CollectionDao
    abstract fun mediaDao(): MediaDao
}
