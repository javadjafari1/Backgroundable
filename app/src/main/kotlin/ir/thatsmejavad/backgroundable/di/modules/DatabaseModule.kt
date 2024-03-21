package ir.thatsmejavad.backgroundable.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.dao.CollectionDao
import ir.thatsmejavad.backgroundable.data.db.dao.MediaDao
import ir.thatsmejavad.backgroundable.data.db.dao.PageKeyDao
import ir.thatsmejavad.backgroundable.data.db.dao.ResourceDao
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(context: Context): BackgroundableDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = BackgroundableDatabase::class.java,
            name = "backgroundable-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesCollectionDao(backgroundableDatabase: BackgroundableDatabase): CollectionDao {
        return backgroundableDatabase.collectionDao()
    }

    @Provides
    @Singleton
    fun provideMediaDao(backgroundableDatabase: BackgroundableDatabase): MediaDao {
        return backgroundableDatabase.mediaDao()
    }

    @Provides
    @Singleton
    fun provideResourceDao(backgroundableDatabase: BackgroundableDatabase): ResourceDao {
        return backgroundableDatabase.resourceDao()
    }

    @Provides
    @Singleton
    fun providePageKeyDao(backgroundableDatabase: BackgroundableDatabase): PageKeyDao {
        return backgroundableDatabase.pageKeyDao()
    }
}
