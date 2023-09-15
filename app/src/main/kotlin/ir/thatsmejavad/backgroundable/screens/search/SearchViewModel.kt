package ir.thatsmejavad.backgroundable.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.sealeds.List
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    val snackbarManager: SnackbarManager,
    private val mediaRepository: MediaRepository,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

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
    val medias: StateFlow<PagingData<Media>> = _medias.asStateFlow()

    val mediaColumnTypeFlow = mediaRepository.mediaColumnTypeFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = List.StaggeredType
    )

    init {
        viewModelScope.launch {
            _searchQuery
                .debounce(1000)
                .collectLatest {
                    if (it.length >= 3) {
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
