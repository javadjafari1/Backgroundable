package ir.thatsmejavad.backgroundable.screens.collectionlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.data.repository.CollectionRepository
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CollectionListViewModel @Inject constructor(
    val snackbarManager: SnackbarManager,
    private val collectionRepository: CollectionRepository,
) : ViewModel() {

    private val _medias = MutableStateFlow<PagingData<Media>>(value = PagingData.empty())
    val medias: StateFlow<PagingData<Media>> = _medias

    fun getMedias(id: String) {
        collectionRepository
            .getCollectionMedias(id)
            .cachedIn(viewModelScope)
            .onEach {
                _medias.emit(it)
            }
            .launchIn(viewModelScope)
    }
}
