package ir.thatsmejavad.backgroundable.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.thatsmejavad.backgroundable.db.converter.MediaTypeConverter
import ir.thatsmejavad.backgroundable.db.converter.ResourceSizeConverter
import ir.thatsmejavad.backgroundable.db.dao.CollectionDao
import ir.thatsmejavad.backgroundable.db.dao.MediaDao
import ir.thatsmejavad.backgroundable.db.dao.ResourceDao
import ir.thatsmejavad.backgroundable.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.db.entity.ResourceEntity

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
