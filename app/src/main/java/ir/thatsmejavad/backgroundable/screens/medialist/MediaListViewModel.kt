package ir.thatsmejavad.backgroundable.screens.medialist

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
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MediaListViewModel @AssistedInject constructor(
    val snackbarManager: SnackbarManager,
    private val mediaRepository: MediaRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val columnCountsPreferences: ColumnCountsPreferences,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<MediaListViewModel>

    private val _medias =
        MutableStateFlow<PagingData<MediaWithResources>>(value = PagingData.empty())
    val medias: StateFlow<PagingData<MediaWithResources>> = _medias

    private val _columnCount = MutableStateFlow(1)
    val columnCount: StateFlow<Int> = _columnCount

    init {
        val id = checkNotNull(savedStateHandle.get<String>("id")) {
            "id should not be null in $this"
        }
        getMedias(id)

        viewModelScope.launch {
            columnCountsPreferences.mediaColumnCountFlow.collectLatest {
                _columnCount.emit(it)
            }
        }
    }

    private fun getMedias(id: String) {
        mediaRepository
            .getMediasByCollectionId(id)
            .cachedIn(viewModelScope)
            .onEach {
                _medias.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun changeColumnCount() {
        viewModelScope.launch {
            val count = if (columnCount.value == 1) 2 else 1
            columnCountsPreferences.setMediaColumnCount(count)
        }
    }
}
