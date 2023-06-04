package ir.thatsmejavad.backgroundable.db.converter

import androidx.room.TypeConverter
import ir.thatsmejavad.backgroundable.core.MediaType

class MediaTypeConverter {
    @TypeConverter
    fun toMediaType(value: String): MediaType {
        return MediaType.fromString(value)
    }

    @TypeConverter
    fun fromMediaType(type: MediaType): String {
        return type.type
    }
}
