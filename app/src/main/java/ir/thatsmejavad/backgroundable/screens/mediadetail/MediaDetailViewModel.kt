package ir.thatsmejavad.backgroundable.screens.mediadetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.core.AsyncJob
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MediaDetailViewModel @AssistedInject constructor(
    val snackbarManager: SnackbarManager,
    private val mediaRepository: MediaRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<MediaDetailViewModel>

    private val _media = MutableStateFlow<AsyncJob<Media>>(value = AsyncJob.Uninitialized)
    val media: StateFlow<AsyncJob<Media>> = _media

    init {
        val id = checkNotNull(savedStateHandle.get<Int>("id")) {
            "id should not be null in $this"
        }
        getMedia(id)
    }

    fun getMedia(mediaId: Int) {
        viewModelScope.launch {
            _media.value = AsyncJob.Loading
            try {
                val result = mediaRepository.getMedia(mediaId)
                _media.value = AsyncJob.Success(result)
            } catch (e: Exception) {
                _media.value = AsyncJob.Fail(e)
            }
        }
    }
}
