package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.data.datastore.ColumnCountsPreferences
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CollectionListViewModel @AssistedInject constructor(
    private val collectionRepository: CollectionRepository,
    val snackbarManager: SnackbarManager,
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val columnCountsPreferences: ColumnCountsPreferences,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<CollectionListViewModel>

    var columnCountPickerData: String = ""

    private val _collections = MutableStateFlow<PagingData<CollectionEntity>>(PagingData.empty())
    val collection: StateFlow<PagingData<CollectionEntity>> = _collections.asStateFlow()

    private val _columnCount = MutableStateFlow(1)
    val columnCount: StateFlow<Int> = _columnCount.asStateFlow()

    init {
        getCollections()
        viewModelScope.launch {
            val items = listOf(1, 2, 3)
            columnCountPickerData = Json.encodeToString(items)
        }

        viewModelScope.launch {
            columnCountsPreferences.collectionColumnCountFlow.collectLatest {
                _columnCount.emit(it)
            }
        }
    }

    fun setColumnCount(count: Int) {
        viewModelScope.launch {
            columnCountsPreferences.setCollectionColumnCount(count)
        }
    }

    private fun getCollections() = collectionRepository
        .getCollections()
        .cachedIn(viewModelScope)
        .onEach {
            _collections.emit(it)
        }
        .launchIn(viewModelScope)
}
