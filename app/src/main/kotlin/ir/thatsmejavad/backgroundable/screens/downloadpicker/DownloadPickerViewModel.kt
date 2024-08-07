package ir.thatsmejavad.backgroundable.screens.downloadpicker

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ir.thatsmejavad.backgroundable.BuildConfig
import ir.thatsmejavad.backgroundable.core.Downloader
import ir.thatsmejavad.backgroundable.core.sealeds.AsyncJob
import ir.thatsmejavad.backgroundable.core.viewmodel.ViewModelAssistedFactory
import ir.thatsmejavad.backgroundable.data.db.entity.ResourceEntity
import ir.thatsmejavad.backgroundable.data.db.relation.MediaWithResources
import ir.thatsmejavad.backgroundable.data.repository.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DownloadPickerViewModel @AssistedInject constructor(
    private val mediaRepository: MediaRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val downloader: Downloader,
) : ViewModel() {
    @AssistedFactory
    interface Factory : ViewModelAssistedFactory<DownloadPickerViewModel>

    private val _media =
        MutableStateFlow<AsyncJob<MediaWithResources>>(AsyncJob.Uninitialized)
    val media: StateFlow<AsyncJob<MediaWithResources>> = _media.asStateFlow()

    init {
        getMedia()
    }

    fun getMedia() {
        val id = requireNotNull(savedStateHandle.get<Int>("id")) {
            "id should not be null in $this"
        }
        viewModelScope.launch {
            _media.value = AsyncJob.Loading
            runCatching {
                mediaRepository.getMediaWithResources(id)?.let { result ->
                    _media.value = AsyncJob.Success(result)
                }
            }.getOrElse {
                _media.value = AsyncJob.Fail(it)
            }
        }
    }

    fun download(resourceEntity: ResourceEntity) {
        val media = (_media.value as AsyncJob.Success).value
        downloader.download(
            url = BuildConfig.IMAGE_SERVER_URL + resourceEntity.url,
            alt = media.media.alt,
            photographer = media.media.photographer,
            size = resourceEntity.size
        )
    }
}
