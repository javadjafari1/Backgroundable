package ir.thatsmejavad.backgroundable.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources

@Dao
interface MediaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedias(medias: List<MediaEntity>)

    @Transaction
    @Query("SELECT * FROM medias WHERE `collection-id` = :collectionId ORDER BY `order-id`")
    fun getPagedMedia(collectionId: String): PagingSource<Int, MediaWithResources>

    @Transaction
    @Query("SELECT * FROM medias WHERE id = :id")
    suspend fun getMediaWithResources(id: Int): MediaWithResources

    @Query("DELETE FROM medias")
    suspend fun deleteAll()
}
