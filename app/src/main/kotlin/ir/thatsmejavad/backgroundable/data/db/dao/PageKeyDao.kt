package ir.thatsmejavad.backgroundable.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.thatsmejavad.backgroundable.data.db.entity.PageKeyEntity

@Dao
interface PageKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPageKey(pageKey: PageKeyEntity)

    @Query("SELECT * FROM `page-key` WHERE `collection-id` = :id")
    suspend fun getPageKeyById(id: String): PageKeyEntity?

    @Query("DELETE FROM `page-key` WHERE `collection-id` = :id")
    suspend fun deletePageKeyById(id: String)
}
