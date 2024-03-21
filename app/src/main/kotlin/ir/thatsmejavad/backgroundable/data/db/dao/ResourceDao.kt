package ir.thatsmejavad.backgroundable.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity

@Dao
interface ResourceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertResources(list: List<ResourceEntity>)

    @Query("DELETE FROM resources")
    suspend fun deleteAll()
}
