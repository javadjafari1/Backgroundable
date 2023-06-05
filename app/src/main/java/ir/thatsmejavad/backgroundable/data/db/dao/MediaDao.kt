package ir.thatsmejavad.backgroundable.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.thatsmejavad.backgroundable.data.db.entity.MediaEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources

@Dao
interface MediaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedias(medias: List<MediaEntity>)

    @Query("SELECT * FROM medias WHERE `collection-id` = :collectionId")
    fun getPagedMedia(collectionId: String): PagingSource<Int, MediaWithResources>

    @Query("SELECT * FROM medias WHERE id = :id")
    suspend fun getMediaWithResources(id: Int): MediaWithResources

    @Query("DELETE FROM medias")
    suspend fun deleteAll()
}
