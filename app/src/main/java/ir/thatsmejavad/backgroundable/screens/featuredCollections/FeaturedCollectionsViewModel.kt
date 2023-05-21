package ir.thatsmejavad.backgroundable.screens.featuredCollections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import ir.thatsmejavad.backgroundable.model.Collection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class FeaturedCollectionsViewModel @Inject constructor(
    private val collectionRepository: CollectionRepository,
    val snackbarManager: SnackbarManager,
) : ViewModel() {

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
