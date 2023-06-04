package ir.thatsmejavad.backgroundable.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ir.thatsmejavad.backgroundable.data.db.BackgroundableDatabase
import ir.thatsmejavad.backgroundable.data.db.dao.CollectionDao
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
        ).build()
    }

    @Provides
    @Singleton
    fun providesCollectionDao(
        backgroundableDatabase: BackgroundableDatabase
    ): CollectionDao = backgroundableDatabase.collectionDao()
}
