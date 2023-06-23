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
            preferences[LAST_LOADED_PAGE] ?: 1
        }

    suspend fun setCollectionColumnCount(count: Int) {
        dataStore.edit { preferences ->
            preferences[LAST_LOADED_PAGE] = count
        }
    }

    companion object PreferencesKeys {
        val LAST_LOADED_PAGE = intPreferencesKey("collection-column-count")
    }
}
