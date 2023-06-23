package ir.thatsmejavad.backgroundable.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import ir.thatsmejavad.backgroundable.core.Constants.USER_PREFERENCES
import javax.inject.Singleton

@Module
object DataStoreModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        appContext: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }
}
