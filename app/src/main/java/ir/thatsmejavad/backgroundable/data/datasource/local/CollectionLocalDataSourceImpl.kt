package ir.thatsmejavad.backgroundable.data.datasource.local

import ir.thatsmejavad.backgroundable.data.db.dao.CollectionDao
import javax.inject.Inject

class CollectionLocalDataSourceImpl @Inject constructor(
    private val collectionDao: CollectionDao
) : CollectionLocalDataSource
