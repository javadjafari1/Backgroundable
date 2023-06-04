package ir.thatsmejavad.backgroundable.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedias(medias: List<MediaEntity>)
}
