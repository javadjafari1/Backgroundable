package ir.thatsmejavad.backgroundable.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity

@Dao
interface ResourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResources(list: List<ResourceEntity>)
}
