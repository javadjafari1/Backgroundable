package ir.thatsmejavad.backgroundable.screens.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SearchViewModel @AssistedInject constructor(
    val snackbarManager: SnackbarManager,
    private val mediaRepository: MediaRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private var searchJob: Job? = null

    private val _medias = MutableStateFlow<PagingData<Media>>(
        PagingData.empty(
            sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(false),
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false),
            )
        )
    )
    val medias: StateFlow<PagingData<Media>> = _medias


    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<SearchViewModel>

    init {
        viewModelScope.launch {
            _searchQuery.collectLatest {
                delay(1.seconds)
                if (it.length > 3) {
                    search()
                }

            }
        }
    }

    fun updateSearchText(text: String) {
        _searchQuery.value = text
        searchJob?.cancel()
        if (text.isEmpty()) {
            removeItems()
        }
    }

    private fun removeItems() {
        _medias.value = PagingData.empty(
            sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(false),
                prepend = LoadState.NotLoading(false),
                append = LoadState.NotLoading(false),
            )
        )
    }

    fun search() {
        searchJob?.cancel()
        searchJob = mediaRepository.searchPhoto(searchQuery.value.trim())
            .cachedIn(viewModelScope)
            .onEach {
                _medias.emit(it)
            }
            .launchIn(viewModelScope)

    }
}
