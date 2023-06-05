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
import ir.thatsmejavad.backgroundable.data.db.entity.CollectionEntity
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CollectionListViewModel @AssistedInject constructor(
    private val collectionRepository: CollectionRepository,
    val snackbarManager: SnackbarManager,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<CollectionListViewModel>

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
