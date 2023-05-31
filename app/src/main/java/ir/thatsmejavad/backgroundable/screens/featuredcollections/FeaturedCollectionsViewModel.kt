package ir.thatsmejavad.backgroundable.screens.featuredcollections

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
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import ir.thatsmejavad.backgroundable.model.Collection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FeaturedCollectionsViewModel @AssistedInject constructor(
    private val collectionRepository: CollectionRepository,
    val snackbarManager: SnackbarManager,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<FeaturedCollectionsViewModel>

    private val _collections = MutableStateFlow<PagingData<Collection>>(value = PagingData.empty())
    val collection: StateFlow<PagingData<Collection>> = _collections

    init {
        getCollections()
    }

    fun getCollections() = collectionRepository
        .getCollections()
        .cachedIn(viewModelScope)
        .onEach {
            _collections.emit(it)
        }
        .launchIn(viewModelScope)
}
