package ir.thatsmejavad.backgroundable.screens.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.SnackbarMessage
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class SearchViewModel @AssistedInject constructor(
    val snackbarManager: SnackbarManager,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

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
    }

    fun search() = viewModelScope.launch {
        snackbarManager.sendError(SnackbarMessage(searchQuery.value))
    }
}
