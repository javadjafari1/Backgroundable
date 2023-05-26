package ir.thatsmejavad.backgroundable.screens.mediadetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.thatsmejavad.backgroundable.core.AsyncJob
import ir.thatsmejavad.backgroundable.core.SnackbarManager
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import ir.thatsmejavad.backgroundable.model.media.Media
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MediaDetailViewModel @Inject constructor(
    val snackbarManager: SnackbarManager,
    private val mediaRepository: MediaRepository,
) : ViewModel() {


    private val _media = MutableStateFlow<AsyncJob<Media>>(value = AsyncJob.Uninitialized)
    val media: StateFlow<AsyncJob<Media>> = _media

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
