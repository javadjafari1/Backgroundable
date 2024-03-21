package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.data.datastore.ColumnCountsPreferences
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class CollectionListViewModel @Inject constructor(
    collectionRepository: CollectionRepository,
    val snackbarManager: SnackbarManager,
    private val columnCountsPreferences: ColumnCountsPreferences,
) : ViewModel() {
    var columnCountPickerData: String = ""
        private set

    val collection: StateFlow<PagingData<CollectionEntity>> = collectionRepository.getCollections()
        .cachedIn(viewModelScope)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = PagingData.empty()
        )

    val columnCount: StateFlow<Int> = columnCountsPreferences.collectionColumnCountFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = 1
        )

    init {
        viewModelScope.launch {
            val items = listOf(1, 2, 3)
            columnCountPickerData = Json.encodeToString(items)
        }
    }

    fun setColumnCount(count: Int) {
        viewModelScope.launch {
            columnCountsPreferences.setCollectionColumnCount(count)
        }
    }
}
