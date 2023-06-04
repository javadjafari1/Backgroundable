package ir.thatsmejavad.backgroundable.db.converter

import androidx.room.TypeConverter
import ir.thatsmejavad.backgroundable.core.ResourceSize

class ResourceSizeConverter {

    @TypeConverter
    fun fromResourceSize(resourceSize: ResourceSize): String {
        return resourceSize.size
    }

    @TypeConverter
    fun toResourceSize(size: String): ResourceSize {
        return ResourceSize.fromString(size)
    }
}