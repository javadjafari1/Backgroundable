package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CollectionListViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    val snackbarManager: SnackbarManager,
) : ViewModel() {

    private val _collections = MutableStateFlow<PagingData<CollectionEntity>>(PagingData.empty())
    val collection: StateFlow<PagingData<CollectionEntity>> = _collections

    init {
        getCollections(false)
    }

    fun getCollections(shouldFetch: Boolean) = collectionRepository
        .getCollections(shouldFetch)
        .cachedIn(viewModelScope)
        .onEach {
            _collections.emit(it)
        }
        .launchIn(viewModelScope)
}
