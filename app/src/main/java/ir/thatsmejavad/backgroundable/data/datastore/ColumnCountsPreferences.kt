package ir.thatsmejavad.backgroundable.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ColumnCountsPreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val collectionColumnCountFlow: Flow<Int>
        get() = dataStore.data.map { preferences ->
            preferences[COLLECTION_COLUMN_COUNT] ?: 1
        }

    suspend fun setCollectionColumnCount(count: Int) {
        dataStore.edit { preferences ->
            preferences[COLLECTION_COLUMN_COUNT] = count
        }
    }

    val mediaColumnCountFlow: Flow<Int>
        get() = dataStore.data.map { preferences ->
            preferences[MEDIA_COLUMN_COUNT] ?: 2
        }

    suspend fun setMediaColumnCount(count: Int) {
        dataStore.edit { preferences ->
            preferences[MEDIA_COLUMN_COUNT] = count
        }
    }

    companion object PreferencesKeys {
        private val COLLECTION_COLUMN_COUNT = intPreferencesKey("collection-column-count")
        private val MEDIA_COLUMN_COUNT = intPreferencesKey("media-column-count")
    }
}
