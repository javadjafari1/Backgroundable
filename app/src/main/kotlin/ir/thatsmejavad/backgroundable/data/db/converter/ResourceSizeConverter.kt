package ir.thatsmejavad.backgroundable.data.db.converter

import androidx.room.TypeConverter
import ir.thatsmejavad.backgroundable.core.sealeds.ResourceSize

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
