package ir.thatsmejavad.backgroundable.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity

@Dao
interface CollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCollections(collections: List<CollectionEntity>)

    @Query("SELECT * FROM collections")
    fun getPagedCollection(): PagingSource<Int, CollectionEntity>

    @Query("DELETE FROM collections")
    suspend fun deleteAll()
}